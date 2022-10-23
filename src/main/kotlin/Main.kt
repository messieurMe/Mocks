import di.MainModule

fun main(args: Array<String>) {
    val module = MainModule(
        baseUrl = "https://www.reddit.com/",
        initalSubreddit = "puppies"
    )

    val data = module.redditRepository.nextPage(limit = 10)
    module.redditRepository.subreddit
    data?.forEach {
        println("Title: \"${it.title}\"\n\tScore:${it.score}")
    }
    println("=====")
    println("Average score: ${module.statistics.getAverageScore(data!!.map { it.score }.toIntArray())}")

}