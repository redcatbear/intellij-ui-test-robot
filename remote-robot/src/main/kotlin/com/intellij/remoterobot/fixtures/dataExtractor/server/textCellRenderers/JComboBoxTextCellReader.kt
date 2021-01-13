package com.intellij.remoterobot.fixtures.dataExtractor.server.textCellRenderers

import com.intellij.remoterobot.fixtures.dataExtractor.server.TextParser
import com.intellij.remoterobot.fixtures.dataExtractor.server.computeOnEdt
import org.assertj.swing.cell.JComboBoxCellReader
import java.awt.Dimension
import javax.annotation.Nonnull
import javax.swing.JComboBox
import javax.swing.JList
import javax.swing.ListCellRenderer

class JComboBoxTextCellReader: JComboBoxCellReader {
    override fun valueAt(@Nonnull comboBox: JComboBox<*>, index: Int): String? {
        return computeOnEdt {
            val item = comboBox.getItemAt(index)
            val renderer = comboBox.renderer as ListCellRenderer<Any>
            val c = renderer.getListCellRendererComponent(JList(), item, index, true, true)
            // fake size to make it paintable
            c.size = Dimension(comboBox.width, 100)
            TextParser.parseCellRenderer(c, true).joinToString(" ") { it.trim() }
        }
    }
}