package abuladze.levan.homework3.model

data class ItemMovie(
        val id : Long,
        val name : String,
        val genres : List<String>,
        val officialSite : String,
        val rating : ItemRating,
        val image : ItemImage,
        val summary : String
) {
    companion object {
        fun transform(input: Movie?) = ItemMovie(
                input?.id ?: 0,
                input?.name ?: "",
                input?.genres ?: arrayListOf(),
                input?.officialSite ?: "",
                ItemRating.transform(input?.rating),
                ItemImage.transform(input?.image),
                input?.summary ?: ""
        )
    }
}

data class ItemRating(val average: Double) {
    companion object {
        fun transform(input: Rating?) = ItemRating(
                input?.average ?: 0.0
        )
    }
}

data class ItemImage(val medium: String, val original: String) {
    companion object {
        fun transform(input: Image?) = ItemImage(
                input?.medium ?: "",
                input?.original ?: ""
        )
    }
}