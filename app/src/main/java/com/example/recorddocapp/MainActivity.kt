 package com.example.recorddocapp


 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.text.KeyboardOptions
 import androidx.compose.material.*
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.text.input.KeyboardType
 import androidx.compose.ui.unit.dp
 import java.time.LocalDate
 import java.time.format.DateTimeFormatter
 import java.time.LocalTime

 class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContent {
             AppointmentApp()
         }
     }
 }

 @Composable
 fun AppointmentApp() {
     var appointments by remember { mutableStateOf(mutableListOf<Appointment>()) }
     var name by remember { mutableStateOf("") }
     var date by remember { mutableStateOf(LocalDate.now()) }
     var time by remember { mutableStateOf(LocalTime.now()) }


     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(16.dp),
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         OutlinedTextField(
             value = name,
             onValueChange = { name = it },
             label = { Text("Ваше имя") },
             modifier = Modifier.fillMaxWidth()
         )
         Spacer(modifier = Modifier.height(16.dp))
         AppointmentDatePicker(selectedDate = date, onDateChange = { date = it })
         Spacer(modifier = Modifier.height(16.dp))
         AppointmentTimePicker(selectedTime = time, onTimeChange = { time = it })
         Spacer(modifier = Modifier.height(16.dp))
         Button(onClick = {
             appointments.add(Appointment(name, date, time))
             name = ""
             time = LocalTime.now()
         }) {
             Text("Записаться")
         }
         Spacer(modifier = Modifier.height(16.dp))
         Text("Ваши записи:")
         appointments.forEach { appointment ->
             Text("Имя: ${appointment.name}, Дата: ${appointment.date.format(DateTimeFormatter.ISO_DATE)}, Время: ${appointment.time.format(DateTimeFormatter.ofPattern("HH:mm"))}")
         }
     }
 }

 data class Appointment(val name: String, val date: LocalDate, val time: LocalTime)

 @Composable
 fun AppointmentDatePicker(selectedDate: LocalDate, onDateChange: (LocalDate) -> Unit) {
     Button(onClick = {
         // Здесь должна быть реализация выбора даты, например, с помощью MaterialDatePicker
         //  В этом упрощенном примере мы просто выводим дату
     }) {
         Text("Выбрать дату: ${selectedDate.format(DateTimeFormatter.ISO_DATE)}")
     }
 }

 @Composable
 fun AppointmentTimePicker(selectedTime: LocalTime, onTimeChange: (LocalTime) -> Unit) {
     Button(onClick = {
         // Здесь должна быть реализация выбора времени, например, с помощью MaterialTimePicker
         // В этом упрощенном примере мы просто выводим время
     }) {
         Text("Выбрать время: ${selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
     }
 }