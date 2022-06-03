package io.github.mishkun.ideavimsneak

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.editor.markup.RangeHighlighter
import io.github.mishkun.ideavimsneak.IdeaVim.myHighlighters
import java.util.function.Consumer

class CursorChangeLsitner : CaretListener {
    override fun caretPositionChanged(event: com.intellij.openapi.editor.event.CaretEvent) {
        super.caretPositionChanged(event)
        println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
        if (IdeaVim.editor != null) {
            val caret = IdeaVim.editor!!.caretModel.currentCaret
            if (caret != null && caret.offset != IdeaVim.currentPosition) {
                myHighlighters.forEach(Consumer { h: RangeHighlighter? -> event.editor.markupModel.removeHighlighter(h!!) })
                myHighlighters.clear()
            }
        }
    }
    fun update(editor: Editor){
        if(IdeaVim.editor != null) {
            val caret = IdeaVim.editor!!.caretModel.currentCaret
            if (caret != null && caret.offset != IdeaVim.currentPosition) {
                myHighlighters.forEach(Consumer { h: RangeHighlighter? -> editor.markupModel.removeHighlighter(h!!) })
                myHighlighters.clear()
            }
        }
    }
}