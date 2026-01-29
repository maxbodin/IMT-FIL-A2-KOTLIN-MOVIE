package movie.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import movie.domain.model.Movie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (movie: Movie) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClick(movie) } // rend la carte entière cliquable avec un effet visuel
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Affiche du film
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true) // Effet de fondu enchaîné
                    .error(android.R.drawable.ic_menu_report_image) // Image en cas d'erreur
                    .placeholder(android.R.drawable.ic_menu_gallery) // Image pendant le chargement
                    .build(),
                contentDescription = "Affiche de ${movie.title}",
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .weight(1f) // Prend l'espace restant
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Année : ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Avec : ${movie.actors}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieCard() {
    MovieCard(
        movie = Movie(
            id = "tt12345",
            title = "Un Titre de Film Très Long Qui Pourrait Dépasser sur Plusieurs Lignes",
            year = 2023,
            actors = "Acteur 1, Actrice 2, Un autre acteur connu",
            posterUrl = "",
            rank = 5
        ),
        onClick = {}
    )
}