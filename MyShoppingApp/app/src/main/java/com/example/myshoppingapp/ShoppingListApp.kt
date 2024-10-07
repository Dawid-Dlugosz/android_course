package com.example.myshoppingapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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


data class  ShoppingListItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingListItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("1") }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { showDialog = true }

        ) {
            Text("Add item")
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ){
            items(sItems) {
                item ->
                if(item.isEditing) {
                    ShoppingItemEditable(
                        item = item,
                        onEditingComplete = {
                            editName, editQuantity ->
                                sItems = sItems.map { it.copy(isEditing = false) }
                                val editedItem = sItems.find { it.id == item.id }
                                editedItem?.let {
                                    it.name = editName
                                    it.quantity = editQuantity
                                }
                        }
                    )
                }else{
                    ShoppingItem(
                        item = item,
                        onEditClick = {
                            sItems = sItems.map { it.copy(isEditing = it.id == item.id) }
                        },
                        onDeleteClick = {
                            sItems = sItems - item
                        }
                    )
                }
            }
//            items(sItems.size) {
//                ShoppingItem(
//                    item = sItems[it],
//                    onEditClick = { },
//                    onDeleteClick = { },
//                    )
//            }
        }
    }
    
    if(showDialog) {
        AlertDialog(
            onDismissRequest = {showDialog = false }, 
            confirmButton = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                if(itemName.isNotBlank()) {
                                    val newItem = ShoppingListItem(
                                        id = sItems.size,
                                        name = itemName,
                                        quantity = itemQuantity.toInt(),
                                    )

                                    sItems = sItems + newItem
                                    itemName = ""
                                    itemQuantity = "1"
                                    showDialog = false
                                }
                            }) {
                                Text(text = "Add")
                            }
                            Button(onClick = {
                                showDialog = false
                            }) {
                                Text(text = "Cancel")
                            }
                        }
            },
            title = { Text(text = "Add shoping item")},
            text =  {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {itemName = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {itemQuantity = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                }

            }
        )
    }
}


@Composable
fun ShoppingItemEditable(
    item: ShoppingListItem,
    onEditingComplete: (String, Int) -> Unit,
) {
    var itemName by remember {
        mutableStateOf(item.name)
    }
    var itemQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }

    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            BasicTextField(
                value = itemName,
                onValueChange = {itemName = it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = itemQuantity,
                onValueChange = {itemQuantity = it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }

        Button(
            onClick = {
                isEditing = false
                onEditingComplete(itemName, itemQuantity.toInt())
            }
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun ShoppingItem(
    item: ShoppingListItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.Cyan),
                shape = RoundedCornerShape(20)
            )
    ) {
        Text(
            text = item.name,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Quantity: ${item.quantity}",
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            IconButton(
                onClick = onEditClick
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}