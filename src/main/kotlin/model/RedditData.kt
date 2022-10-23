package model

class SubredditResponse(
    val kind: String, val data: SubredditData
) {
    class SubredditData(
        val after: String, val children: List<SubredditPostResponse>
    ) {
        class SubredditPostResponse(
            val kind: String,
            val data: SubredditPosts
        )
        class SubredditPosts(
            val name: String, val title: String, val ups: Int, val score: Int
        )
    }
}

//{"data": {"children": [{"kind": "t3", "data": {"approved_at_utc": null, "subreddit": "puppies", "selftext": "", "author_fullname":