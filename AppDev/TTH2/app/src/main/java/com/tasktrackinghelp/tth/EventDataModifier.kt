package com.tasktrackinghelp.tth

import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

class EventDataModifier(
    val event: PositionedEvent,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = event
}