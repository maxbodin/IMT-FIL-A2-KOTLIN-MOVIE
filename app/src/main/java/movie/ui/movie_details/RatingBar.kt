package movie.ui.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    maxStars: Int = 5,
    starColor: Color = Color(0xFFFFC107)
) {
    val coercedRating: Int = rating.coerceIn(0, maxStars)
    val emptyStars = maxStars - coercedRating

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(coercedRating) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starColor)
        }
        repeat(emptyStars) {
            Icon(imageVector = Icons.Filled.StarBorder, contentDescription = null, tint = starColor)
        }

        Text(
            text = coercedRating.toString(),
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}


@Preview
@Composable
fun RatingBarPreview() {
    Column {
        RatingBar(rating = 4)
        RatingBar(rating = 3)
        RatingBar(rating = 2)
        RatingBar(rating = 1)
        RatingBar(rating = 5)
        RatingBar(rating = 0)
    }
}