package com.thoughtworks.car.dashboard.ui.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thoughtworks.car.R

@Composable
fun StatusView(modifier: Modifier = Modifier, statusUiState: StatusUiState) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.size(73.dp, 65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_window_frame),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_window_stripe),
                        modifier = modifier.offset(x = 5.dp, y = 6.dp),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 3.dp))

                Image(
                    painter = painterResource(id = R.drawable.window_lock),
                    contentDescription = null
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.size(73.dp, 65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fragrance),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(vertical = 3.dp))

                Image(
                    painter = painterResource(id = R.drawable.fragrance),
                    contentDescription = null
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.size(73.dp, 65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fragrance),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(vertical = 3.dp))

                Image(
                    painter = painterResource(id = R.drawable.fragrance),
                    contentDescription = null
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.size(73.dp, 65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fragrance),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(vertical = 3.dp))

                Image(
                    painter = painterResource(id = R.drawable.fragrance),
                    contentDescription = null
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.size(73.dp, 65.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fragrance),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(vertical = 3.dp))

                Image(
                    painter = painterResource(id = R.drawable.fragrance),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewStatusView() {
    StatusView(statusUiState = StatusUiState())
}
