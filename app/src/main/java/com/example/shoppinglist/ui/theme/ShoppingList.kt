package com.example.shoppinglist.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItems(val id:Int, var name:String, var quantity:Int, var isEditing:Boolean = false)

@Composable
fun ShoppingList(){


    var sItems by remember { mutableStateOf(listOf<ShoppingItems>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showDialog=true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Add Items!")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sItems){
                ShoppingListItems(item = it, {}, {})
            }
        }
    }

    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog=false },
            confirmButton = {
                // add the button Add / Cancel
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    // Add Button
                    Button(onClick = {
                        if (itemName.isNotBlank()){
                            val newItems = ShoppingItems(
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            sItems = sItems + newItems
                            showDialog = false
                            itemName = ""
                        }
                    }) {
                        Text(text = "Add Item")
                    }

                    // Cancel Button
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                } }, title = { Text(
            text = "Add Shopping Items")},
             text = {
                 Column {
                     // itemName
                     OutlinedTextField(value = itemName, onValueChange ={
                         itemName = it
                     }, singleLine = true,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(8.dp))

                     // itemQuantity
                     OutlinedTextField(value = itemQuantity, onValueChange ={
                         itemQuantity = it
                     }, singleLine = true,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(8.dp))

                 }
             })
    }
}


@Composable
fun ShoppingListItems( item: ShoppingItems,
                       onEditClick: () -> Unit,
                       onDeleteClick: () -> Unit
                       ){
Row(modifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()
    .border(
        border = BorderStroke(2.dp, Color(0XFF018786)),
        shape = RoundedCornerShape(20)
    )) {
    Text(text = item.name, modifier = Modifier.padding(8.dp))
}
}

