
fun main() {
    drawBoard(table)
    while (true) {
        takeInput()
        drawBoard(table)
        if (checkEnd(table, currentPlayer) && thereIsAWinner) {
            println("$currentPlayer wins")
            return
        } else if (checkEnd(table, currentPlayer)) {
            println("Draw")
            return
        }
        currentPlayer = if (playerMoveCounter % 2 == 0) "X" else "O"
    }





}
var playerMoveCounter = 0
var currentPlayer = "X"
var thereIsAWinner = false

val table = MutableList(3) {
    MutableList(3) {" "}
}

fun drawBoard(table: MutableList<MutableList<String>>) {
    println("---------")
    for (i in 0..2) {
        for (j in 0..2) {
            if (j == 0) print("| ")
            print("${table[i][j]} ")
            if (j == 2) print("|")
        }
        println()
    }
    println("---------\n")
}

fun takeInput() {
    println("$currentPlayer's turn...")
    print("Type the coordinates you want to play as in \"X Y\", like \"2 1\" or \"1 3\": ")
    do {
        val (horizontal, vertical) = readln().split(" ").map { it.toInt() }
        val isNumber = vertical in 0..9 && horizontal in 0..9
        val isInLimits = vertical in 1..3 && horizontal in 1..3
        var isValid = false
        if (!isNumber) {
            println("You should enter numbers!")
            continue
        } else if (!isInLimits) {
            println("Coordinates should be from 1 to 3!")
            continue
        }else if (table[vertical - 1][horizontal - 1] != " ") {
            println("This cell is occupied! Choose another one!")
            continue
        } else {
            isValid = true
            table[vertical - 1][horizontal - 1] = currentPlayer
            playerMoveCounter++
        }
    } while (!isValid)
}

fun checkEnd(table: MutableList<MutableList<String>>, player: String): Boolean {
    if (table[0][0] == player && table[1][0] == player && table[2][0] == player) thereIsAWinner = true
    if (table[0][1] == player && table[1][1] == player && table[2][1] == player) thereIsAWinner = true
    if (table[0][2] == player && table[1][2] == player && table[2][2] == player) thereIsAWinner = true
    if (table[0][0] == player && table[0][1] == player && table[0][2] == player) thereIsAWinner = true
    if (table[1][0] == player && table[1][1] == player && table[1][2] == player) thereIsAWinner = true
    if (table[2][0] == player && table[2][1] == player && table[2][2] == player) thereIsAWinner = true
    if (table[0][0] == player && table[1][1] == player && table[2][2] == player) thereIsAWinner = true
    if (table[2][0] == player && table[1][1] == player && table[0][2] == player) thereIsAWinner = true
    if (!thereIsAWinner && !table[0].joinToString("").contains(" ")
        && !table[1].joinToString("").contains(" ")
        && !table[2].joinToString("").contains(" ")) {
        return true
    }

    return thereIsAWinner
}
