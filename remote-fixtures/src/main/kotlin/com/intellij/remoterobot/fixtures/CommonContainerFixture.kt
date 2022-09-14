package com.intellij.remoterobot.fixtures

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.search.locators.Locator
import java.time.Duration

/*
    for base fixtures we need at least these methods:

        component(locator: Locator, timeout: Duration): ComponentFixture { findWithTimeout.... }  - universal way of finding one component
        components(locator: Locator): List<ComponentFixture> { findAll.... }  - universal way of finding list of components

    and also we can have compact calls for frequently used locators:
        component(text: String): ComponentFixture = component(ComponentFixture.byText(text)) - example of a compact method

 */

@Suppress("MemberVisibilityCanBePrivate")
open class CommonContainerFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent
) : ContainerFixture(remoteRobot, remoteComponent) {
    companion object {
        private val defaultFindTimeout = Duration.ofSeconds(5)
    }

    //----------------------------------------

    @JvmOverloads
    fun actionLink(locator: Locator, timeout: Duration = defaultFindTimeout): ActionLinkFixture =
        find(locator, timeout)


    fun actionLinks(locator: Locator): List<ActionLinkFixture> =
        findAll(locator)

    fun actionLink(text: String) = actionLink(ActionLinkFixture.byText(text))

    //----------------------------------------

    fun button(text: String): JButtonFixture = button(JButtonFixture.byText(text))

    @JvmOverloads
    fun button(locator: Locator, timeout: Duration = defaultFindTimeout): JButtonFixture {
        return find(locator, timeout)
    }

    fun buttons(locator: Locator): List<JButtonFixture> {
        return findAll(locator)
    }

    //----------------------------------------

    @JvmOverloads
    fun jLabel(locator: Locator, timeout: Duration = defaultFindTimeout): JLabelFixture =
        find(locator, timeout)

    fun jLabels(locator: Locator): List<JLabelFixture> =
        findAll(locator)

    fun jLabel(text: String) = jLabel(JLabelFixture.byText(text))

    //----------------------------------------

    @JvmOverloads
    fun textField(locator: Locator, timeout: Duration = defaultFindTimeout): JTextFieldFixture =
        find(locator, timeout)

    fun textFields(locator: Locator): List<JTextFieldFixture> =
        findAll(locator)

    @JvmOverloads
    fun textField(labelText: String, contains: Boolean = true) = textField(
        if (contains) {
            JTextFieldFixture.byLabel(jLabel(JLabelFixture.byContainsText(labelText)))
        } else {
            JTextFieldFixture.byLabel(jLabel(JLabelFixture.byText(labelText)))
        }
    )

    //----------------------------------------

    @JvmOverloads
    fun textArea(locator: Locator = JTextAreaFixture.byType(), timeout: Duration = defaultFindTimeout): JTextAreaFixture =
        find(locator, timeout)

    fun textAreas(locator: Locator): List<JTextAreaFixture> =
        findAll(locator)

    //----------------------------------------

    @JvmOverloads
    fun comboBox(locator: Locator, timeout: Duration = defaultFindTimeout): ComboBoxFixture =
        find(locator, timeout)

    fun comboBoxes(locator: Locator): List<ComboBoxFixture> =
        findAll(locator)

    @JvmOverloads
    fun comboBox(labelText: String, contains: Boolean = true) = comboBox(
        if (contains) {
            ComboBoxFixture.byLabel(jLabel(JLabelFixture.byContainsText(labelText)))
        } else {
            ComboBoxFixture.byLabel(jLabel(JLabelFixture.byText(labelText)))
        }
    )

    //----------------------------------------

    @JvmOverloads
    fun actionButton(
        locator: Locator = ActionButtonFixture.byType(),
        timeout: Duration = Duration.ofSeconds(5)
    ): ActionButtonFixture =
        find(locator, timeout)

    @JvmOverloads
    fun actionButtons(
        locator: Locator = ActionButtonFixture.byType()
    ): List<ActionButtonFixture> =
        findAll(locator)

    //----------------------------------------

    @JvmOverloads
    fun checkBox(locator: Locator, timeout: Duration = defaultFindTimeout): JCheckboxFixture =
        find(locator, timeout)

    fun checkBoxes(locator: Locator): List<JCheckboxFixture> =
        findAll(locator)

    @JvmOverloads
    fun checkBox(text: String, contains: Boolean = false) = checkBox(
        if (contains)
            JCheckboxFixture.byTextContains(text)
        else
            JCheckboxFixture.byText(text)
    )

    //----------------------------------------

    @JvmOverloads
    fun radioButton(locator: Locator, timeout: Duration = defaultFindTimeout): JRadioButtonFixture =
        find(locator, timeout)

    fun radioButtons(locator: Locator): List<JRadioButtonFixture> =
        findAll(locator)

    fun radioButton(text: String) = radioButton(JRadioButtonFixture.byText(text))

    //----------------------------------------

    @JvmOverloads
    fun jList(
        locator: Locator,
        timeout: Duration = defaultFindTimeout,
        func: JListFixture.() -> Unit = {}
    ): JListFixture =
        find<JListFixture>(locator, timeout).apply(func)

    @JvmOverloads
    fun jLists(locator: Locator = JListFixture.byType()): List<JListFixture> = findAll(locator)

    @JvmOverloads
    fun jList(func: JListFixture.() -> Unit = {}) = jList(JListFixture.byType(), func = func)

    //----------------------------------------

    @JvmOverloads
    fun jTree(
        locator: Locator = JTreeFixture.byType(),
        timeout: Duration = defaultFindTimeout,
        func: JTreeFixture.() -> Unit = {}
    ): JTreeFixture =
        find<JTreeFixture>(locator, timeout).apply(func)

    @JvmOverloads
    fun jTrees(locator: Locator = JTreeFixture.byType()): List<JTreeFixture> = findAll(locator)

    //----------------------------------------
    @JvmOverloads
    fun jMenuBar(func: JMenuBarFixture.() -> Unit = {}) = find<JMenuBarFixture>(JMenuBarFixture.byType()).apply(func)

    @JvmOverloads
    fun jMenuBar(locator: Locator, func: JMenuBarFixture.() -> Unit = {}) = find<JMenuBarFixture>(locator).apply(func)

    //----------------------------------------

    @JvmOverloads
    fun jPopupMenus(locator: Locator= JPopupMenuFixture.byType()): List<JPopupMenuFixture> = findAll(locator)

    @JvmOverloads
    fun jPopupMenu(timeout: Duration = defaultFindTimeout, func: JPopupMenuFixture.() -> Unit = {}) =
        find<JPopupMenuFixture>(JPopupMenuFixture.byType(), timeout).apply(func)

    @JvmOverloads
    fun jPopupMenu(locator: Locator, timeout: Duration = defaultFindTimeout, func: JPopupMenuFixture.() -> Unit = {}) =
        find<JPopupMenuFixture>(locator, timeout).apply(func)

    //----------------------------------------

    @JvmOverloads
    fun browser(timeout: Duration = defaultFindTimeout): JCefBrowserFixture {
        val locator = if (remoteRobot.isMac()) {
            JCefBrowserFixture.macLocator
        } else {
            JCefBrowserFixture.canvasLocator
        }
        return find(locator, timeout)
    }

    @JvmOverloads
    fun browser(locator: Locator, timeout: Duration = defaultFindTimeout): JCefBrowserFixture =
        find(locator, timeout)

    //----------------------------------------

    @JvmOverloads
    fun textEditor(locator: Locator, timeout: Duration = defaultFindTimeout): TextEditorFixture =
        find(locator, timeout)

    @JvmOverloads
    fun textEditor(timeout: Duration = defaultFindTimeout): TextEditorFixture {
        return textEditor(TextEditorFixture.locator, timeout)
    }

    @JvmOverloads
    fun textEditors(locator: Locator = TextEditorFixture.locator): List<TextEditorFixture> =
        findAll(locator)

    //----------------------------------------

    @JvmOverloads
    fun heavyWeightWindow(locator: Locator, timeout: Duration = defaultFindTimeout): HeavyWeightWindowFixture =
        find(locator, timeout)

    @JvmOverloads
    fun heavyWeightWindow(timeout: Duration = defaultFindTimeout): HeavyWeightWindowFixture =
        heavyWeightWindow(HeavyWeightWindowFixture.byXpath, timeout)

    @JvmOverloads
    fun heavyWeightWindows(locator: Locator = HeavyWeightWindowFixture.byXpath): List<HeavyWeightWindowFixture> =
        findAll(locator)
}
