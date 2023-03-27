package com.example.icecream

import android.os.Bundle
import android.util.Log.e
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.icecream.repo.Repository
import com.example.icecream.screens.HomeScreen
import com.example.icecream.ui.theme.IceCreamTheme

class MainActivity : ComponentActivity() {
    private lateinit var factory: Factory
    private lateinit var viewModel: ViewModel
    private lateinit var repo: Repository
    private lateinit var url: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            repo = Repository()
            factory = Factory(repo)
            viewModel = ViewModelProvider(this, factory)[ViewModel::class.java]
            IceCreamTheme {
                viewModel.getUrl()
                viewModel.signIn()
                viewModel.observeUrl().observe(this) {
                    url = it
                    e("url", it)
                }
                viewModel.observeUid().observe(this){e("uid",it)}
                viewModel.observeChat().observe(this) {
                    it?.apply {
                        e("chat", it.choices[0].text)
                    }
                    if(it==null) e("chat","no response")
                }


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen() {
                        viewModel.getChat(it, url)
                    }
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IceCreamTheme {
        HomeScreen() {}
    }
}