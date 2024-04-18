package com.m7019e.tmdbbrowser.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m7019e.tmdbbrowser.R
import com.m7019e.tmdbbrowser.model.AuthorDetails
import com.m7019e.tmdbbrowser.model.Review
import com.m7019e.tmdbbrowser.ui.ReviewUiState


@Composable
fun MovieReviewsScreen(reviewUiState: ReviewUiState) {
    when (reviewUiState) {
        is ReviewUiState.Success -> {
            LazyColumn {
                items(reviewUiState.reviews) { review ->
                    ReviewCard(review = review)
                }
            }
        }

        is ReviewUiState.Loading -> {
            Text(
                text = stringResource(id = R.string.api_loading_message),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        is ReviewUiState.Error -> {
            Text(
                text = stringResource(id = R.string.api_error_message),
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewCard(review: Review, modifier: Modifier = Modifier) {
    OutlinedCard(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = review.author, style = MaterialTheme.typography.headlineSmall)
                    Text(text = review.date, style = MaterialTheme.typography.labelSmall)
                }
                Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(id = R.string.icon_star)
                    )
                    Text(
                        text = review.rating.rating.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = review.content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewCardPreview() {
    ReviewCard(
        Review(
            "author",
            "lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content lots of content ",
            "2024-01-01",
            AuthorDetails(7f)
        )
    )
}
