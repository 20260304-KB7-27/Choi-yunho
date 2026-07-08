<script setup>
import { watch, onBeforeUnmount } from 'vue';
import { EditorContent, useEditor } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import Link from '@tiptap/extension-link';
import Placeholder from '@tiptap/extension-placeholder';
import TextAlign from '@tiptap/extension-text-align';
import Underline from '@tiptap/extension-underline';

const model = defineModel({
  type: String,
  default: '',
});

const editor = useEditor({
  content: model.value,
  extensions: [
    StarterKit,
    Underline,
    Link.configure({
      openOnClick: false,
      autolink: true,
      defaultProtocol: 'https',
    }),
    Placeholder.configure({
      placeholder: '내용을 입력하세요',
    }),
    TextAlign.configure({
      types: ['heading', 'paragraph'],
    }),
  ],
  onUpdate: ({ editor }) => {
    const html = editor.getHTML();
    model.value = html === '<p></p>' ? '' : html;
  },
});

watch(model, (value) => {
  if (!editor.value) return;
  if (value === editor.value.getHTML()) return;

  editor.value.commands.setContent(value || '', false);
});

onBeforeUnmount(() => {
  editor.value?.destroy();
});

const run = (command) => {
  if (!editor.value) return;
  command(editor.value.chain().focus()).run();
};

const toggleLink = () => {
  if (!editor.value) return;

  const previousUrl = editor.value.getAttributes('link').href;
  const url = window.prompt('링크 URL을 입력하세요.', previousUrl || 'https://');

  if (url === null) return;

  if (url === '') {
    editor.value.chain().focus().extendMarkRange('link').unsetLink().run();
    return;
  }

  editor.value.chain().focus().extendMarkRange('link').setLink({ href: url }).run();
};
</script>

<template>
  <div class="tiptap-editor">
    <div v-if="editor" class="editor-toolbar" aria-label="편집 도구">
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('heading', { level: 2 }) }"
        title="제목"
        @click="run((chain) => chain.toggleHeading({ level: 2 }))"
      >
        H
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('bold') }"
        title="굵게"
        @click="run((chain) => chain.toggleBold())"
      >
        <i class="fa-solid fa-bold"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('italic') }"
        title="기울임"
        @click="run((chain) => chain.toggleItalic())"
      >
        <i class="fa-solid fa-italic"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('underline') }"
        title="밑줄"
        @click="run((chain) => chain.toggleUnderline())"
      >
        <i class="fa-solid fa-underline"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('bulletList') }"
        title="글머리 목록"
        @click="run((chain) => chain.toggleBulletList())"
      >
        <i class="fa-solid fa-list-ul"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('orderedList') }"
        title="번호 목록"
        @click="run((chain) => chain.toggleOrderedList())"
      >
        <i class="fa-solid fa-list-ol"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        title="왼쪽 정렬"
        @click="run((chain) => chain.setTextAlign('left'))"
      >
        <i class="fa-solid fa-align-left"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        title="가운데 정렬"
        @click="run((chain) => chain.setTextAlign('center'))"
      >
        <i class="fa-solid fa-align-center"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        title="오른쪽 정렬"
        @click="run((chain) => chain.setTextAlign('right'))"
      >
        <i class="fa-solid fa-align-right"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        :class="{ active: editor.isActive('link') }"
        title="링크"
        @click="toggleLink"
      >
        <i class="fa-solid fa-link"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        title="되돌리기"
        @click="run((chain) => chain.undo())"
      >
        <i class="fa-solid fa-rotate-left"></i>
      </button>
      <button
        type="button"
        class="tool-button"
        title="다시 실행"
        @click="run((chain) => chain.redo())"
      >
        <i class="fa-solid fa-rotate-right"></i>
      </button>
    </div>

    <editor-content :editor="editor" />
  </div>
</template>

<style scoped>
.tiptap-editor {
  border: 1px solid #dee2e6;
  border-radius: 6px;
  background-color: #fff;
}

.editor-toolbar {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  padding: 8px;
  border-bottom: 1px solid #dee2e6;
  background-color: #f8f9fa;
}

.tool-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border: 1px solid transparent;
  border-radius: 4px;
  background-color: transparent;
  color: #212529;
}

.tool-button:hover {
  border-color: #ced4da;
  background-color: #fff;
}

.tool-button.active {
  border-color: #0d6efd;
  background-color: #e7f1ff;
  color: #0d6efd;
}

:deep(.tiptap) {
  min-height: 260px;
  padding: 12px;
  outline: none;
}

:deep(.tiptap p) {
  margin-bottom: 0.75rem;
}

:deep(.tiptap h2) {
  margin: 0.75rem 0;
  font-size: 1.5rem;
  font-weight: 700;
}

:deep(.tiptap ul),
:deep(.tiptap ol) {
  padding-left: 1.5rem;
}

:deep(.tiptap p.is-editor-empty:first-child::before) {
  content: attr(data-placeholder);
  float: left;
  height: 0;
  color: #adb5bd;
  pointer-events: none;
}
</style>
