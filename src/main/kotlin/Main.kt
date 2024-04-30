fun main(args: Array<String>) {
    val archives = mutableListOf<Archive>()

    fun viewNote(note: Note) {
        println("\n${note.name}\n" +
                "\n${note.content}")
        println("\nНажмите любую кнопку для возврата к списку заметок")
        readlnOrNull()
    }

    fun createNote(archive: Archive) {
        println("Введите название заметки:")
        val title = readlnOrNull() ?: return
        if (title.isNotBlank()) {
            println("Введите содержимое заметки:")
            val content = readlnOrNull() ?: return
            if (content.isNotBlank()) {
                archive.notes.add(Note(title, content))
                println("Заметка '$title' успешно добавлена.")
            } else {
                println("Содержимое заметки не может быть пустым.")
            }
        } else {
            println("Название заметки не может быть пустым.")
        }
    }

    fun chooseNote(archive: Archive) {
        val notesMenuItems = archive.notes.map { note ->
            MenuItem(note.name) {
                viewNote(note)
            }
        }.toMutableList().apply {
            add(MenuItem("Создать новую заметку") { createNote(archive) })
            add(MenuItem("Вернуться к выбору архива") { return@MenuItem })
        }

        Menu("Выбор заметки из архива '${archive.name}'", notesMenuItems).showMenuOptions()
    }

    fun createArchive() {
        println("Введите название нового архива:")
        val name = readlnOrNull() ?: return
        if (name.isNotBlank()) {
            archives.add(Archive(name))
            println("Архив '$name' успешно создан")
        } else {
            println("Название архива не может быть пустым")
        }
    }

    fun chooseArchive() {
        val archiveMenuItems = archives.map { archive ->
            MenuItem(archive.name) {
                chooseNote(archive)
            }
        }.toMutableList().apply {
            add(MenuItem("Создать новый архив") { createArchive() })
            add(MenuItem("Вернуться в главное меню") { return@MenuItem })
        }

        Menu("Выбор архива", archiveMenuItems).showMenuOptions()
    }

    val mainMenuItems = mutableListOf(
            MenuItem("Выбрать архива") { chooseArchive() },
            MenuItem("Создать архива") { createArchive() },
            MenuItem("Выход") { println("Выход из программы") }
    )
    val mainMenu = Menu("Главное меню", mainMenuItems)
    mainMenu.showMenuOptions()


}