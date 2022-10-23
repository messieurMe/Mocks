import di.MainModule

fun main(args: Array<String>) {
    val module = MainModule(
        baseUrl = "https://www.reddit.com/",
        initalSubreddit = "puppies"
    )

    var data = module.redditRepository.nextPage(limit = 10)
    data?.forEach {
        println("Title: \"${it.title}\"\n\tScore:${it.score}")
    }
    println("\n===\nNext data\n===\n")
    data = module.redditRepository.nextPage(10)
    data?.forEach {
        println("Title: \"${it.title}\"\n\tScore:${it.score}")
    }
    println("=====")
    println("Average score: ${module.statistics.getAverageScore(data!!.map { it.score }.toIntArray())}")

}