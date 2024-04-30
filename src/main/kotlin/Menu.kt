import java.util.Scanner

class Menu(private val title: String, private val items: MutableList<MenuItem>) {
    private val scanner = Scanner(System.`in`)

    fun showMenuOptions() {
        while (true) {
            println("\n$title")
            for (i in items.indices) {
                println("$i. ${items[i].name}")
            }
            print("Введите номер действия: ")

            val input = scanner.nextLine()

            val choice = input?.toIntOrNull()
            if (choice == null || choice !in items.indices) {
                println("Ошибка: введите коррекый номер.")
            } else {
                items[choice].action()
                if (choice == items.lastIndex) {
                    break
                }
            }
        }
    }
}