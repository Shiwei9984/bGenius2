/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cupcake.ui.OrderViewModel

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
enum class CupCakeScreen(val title:Int){
    Start(title=R.string.app_name),
    Flavor(title=R.string.choose_flavor),
    Pickup(title=R.string.choose_pickup_date),
    Summary(title=R.string.order_summary)
}
@Composable
fun CupcakeAppBar(
    currentScreen:CupCakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun CupcakeApp(modifier: Modifier = Modifier, viewModel: OrderViewModel = viewModel()){

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CupCakeScreen.valueOf(
        backStackEntry?.destination?.route ?:CupCakeScreen.Start.name
    ),
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CupcakeAppBar(
                canNavigateBack = false,
                navigateUp = {navController.navigateUp()},
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = CupCakeScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = CupCakeScreen.Start.name){
                StartOrderScreen(
                    quantityOptions = quantityOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CupCakeScreen.Flavor.name)
                    }
                )
            }
            composable(route = CupcakeScreen.Flavor.name){
                val context = LocalContent.current
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = {
                        navcontroller.navigate(CupCakeScreen.Pickup.name)
                    },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    options = flavor.map{id->
                        context.resources.getString(id)
                    },
                    onSelectionChanged = {viewModel.setFlavor(it)}
                )
            }
            composable(route = CupcakeScreen.Pickup.name){
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = {navController.navigate(CupCakeScreen.Summary.name)},
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    options = uiState.pickupOptions,
                    onSelectionChanged = {viewModel.setDate(it)}
                )
            }
            composable(route = CupcakeScreen.Summary.name){
                val context = LocalContext.current
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onSendButtonClicked = {subject:String, summary:String -> shareOrder(context, subject=subject,summary=summary)}
                )
            }
        }
    }
}

private fun shareOrder(context: Context, subject:String, summary:String){
    val intent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.new_cupcake_order)
            )
        )
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController){
    viewModel.resetOrder()
    navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)
}

