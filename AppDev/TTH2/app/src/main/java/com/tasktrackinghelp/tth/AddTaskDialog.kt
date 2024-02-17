package com.tasktrackinghelp.tth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.isDigitsOnly
import com.tasktrackinghelp.tth.DateDefaults.DATE_LENGTH
import com.tasktrackinghelp.tth.DateDefaults.DATE_MASK
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customTextField(value: String, onValueChange: (String) -> Unit){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier.padding(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = Color.Black,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black
        )

    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMultilineText(value: String, onValueChange: (String) -> Unit){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = false,
        modifier = Modifier.padding(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = Color.Black,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(date: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = date,
        onValueChange = onValueChange,
        label = {Text("")},
        placeholder = {Text("DD/MM/YYYY")},
        visualTransformation = MaskVisualTransformation(DATE_MASK),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.padding(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = Color.Black,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black,
            disabledPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Gray,
        )
    )

}

object DateDefaults {
    const val DATE_MASK = "##/##/####"
    const val DATE_LENGTH = 8 // Equals to "##/##/####".count { it == '#' }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun time(
    selectedHour: Int,
    selectedMinute: Int,
    label: String,
    modifier: Modifier,
    onTimeSelected: (Int, Int) -> Unit,

){


    var showDialog by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    val formattedTime = "${selectedHour.toString().padStart(2, '0')} : ${selectedMinute.toString().padStart(2, '0')}"

    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 12.dp)
                ),
            onDismissRequest = { showDialog = false }
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f)
                    )
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // time picker
                TimeInput(state = timePickerState
                ,colors = TimePickerColors(
                        clockDialColor = MaterialTheme.colorScheme.primary,
                        selectorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primary,
                periodSelectorBorderColor = MaterialTheme.colorScheme.primary,
                clockDialSelectedContentColor =MaterialTheme.colorScheme.onPrimary,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.onPrimary,
                periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary,
                timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.onPrimary,
                timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.onPrimary,
                timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary
                )

                )


                // buttons
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // dismiss button
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Dismiss")
                    }

                    // confirm button
                    TextButton(
                        onClick = {
                            showDialog = false
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }

   Row(
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement =  Arrangement.spacedBy(25.dp)
   ){
       OutlinedTextField(value = formattedTime,
           label = { Text(text = label) },
           trailingIcon = { IconButton(onClick = {
               showDialog = true
           }) {
               Icon(Icons.Default.Edit, contentDescription = null)}
           },
           onValueChange = {
           },
           enabled = false,
           modifier = Modifier
               .width(100.dp)
               .height(60.dp),
           colors = OutlinedTextFieldDefaults.colors(
               focusedBorderColor = MaterialTheme.colorScheme.primary,
               focusedTextColor = Color.Black,
               cursorColor = Color.Black,
               unfocusedTextColor = Color.Black,
               disabledPlaceholderColor = Color.Black,
               unfocusedPlaceholderColor = Color.Gray,
               disabledTextColor = Color.Gray,
               disabledBorderColor = Color.Gray,
               disabledLabelColor = Color.Gray,
               disabledTrailingIconColor = MaterialTheme.colorScheme.primary
           )
       )
   }



    fun parseDate(input: String): LocalDate? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate.parse(input, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
@Composable
fun AddTaskDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit,
    onConfirm: (Event) -> Unit,
) {
    // State variables for each input field
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskDate by remember { mutableStateOf("") } // Use a suitable format, e.g., "yyyy-MM-dd"
    var startTime by remember { mutableStateOf(LocalTime.now()) }
    var endTime by remember { mutableStateOf(LocalTime.now().plusHours(1)) }

    var startTimeHour by remember { mutableStateOf(0) }
    var startTimeMinute by remember { mutableStateOf(0) }
    var endTimeHour by remember { mutableStateOf(0) }
    var endTimeMinute by remember { mutableStateOf(0) }


    // Check if all required fields are filled
    val isFormValid = taskName.isNotBlank() && taskDate.isNotBlank()
            && taskDescription.isNotBlank() // Add other checks if needed
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Dialog(
        onDismissRequest = {
            onDismiss
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp)),
            colors = CardColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary,
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                ,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Add Task",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    //Task Name
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Task Name")
                        customTextField(
                            value = taskName,
                            onValueChange = { taskName = it }
                        )
                    }
                    //Task Date
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text("Task Date")
                        DateTextField(
                            date = taskDate,
                            onValueChange = {
                                if (it.length <= DATE_LENGTH) {
                                    if (it.isDigitsOnly()) taskDate = it
                                    taskDate = it
                                }
                            }
                        )
                    }
                    //Task Time
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Task Time")
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            time(startTimeHour, startTimeMinute, label = "Start", modifier = Modifier.padding(horizontal = 5.dp)) { hour, minute ->
                                startTimeHour = hour
                                startTimeMinute = minute

                            }
                            time(endTimeHour, endTimeMinute, label = "End", modifier = Modifier.padding(horizontal = 5.dp)) { hour, minute ->
                                endTimeHour = hour
                                endTimeMinute = minute
                            }
                        }

                    }
                    //Task Description
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Task Description")
                        CustomMultilineText(
                            value = taskDescription,
                            onValueChange = { taskDescription = it }
                        )
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .padding(20.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Button(
                            onClick = {
                                // Parse the date input and construct LocalDateTime for start and end
                                val parsedDate = try {
                                    LocalDate.parse(taskDate, dateFormatter)
                                } catch (e: DateTimeParseException) {
                                    null
                                }

                                if (parsedDate != null) {
                                    val startDateTime = LocalDateTime.of(parsedDate, LocalTime.of(startTimeHour, startTimeMinute))
                                    val endDateTime = LocalDateTime.of(parsedDate, LocalTime.of(endTimeHour, endTimeMinute))

                                    // Create an Event object from the state variables
                                    val event = Event(
                                        name = taskName,
                                        color = Color(0xFFAFBBF2), // Adjust the color as needed
                                        start = LocalDateTime.now(),
                                        end = LocalDateTime.now(),
                                        description = taskDescription
                                    )
                                    onConfirm(event) // Assuming onConfirm adds the event to a list or ViewModel
                                    viewModel.addEvent(event)
                                }
                                onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.LightGray
                        ),
                        modifier = Modifier
                            .padding(20.dp),
                        shape = RoundedCornerShape(8.dp),
                        enabled = isFormValid
                    ) {
                        Text(
                            text = "Confirm",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

