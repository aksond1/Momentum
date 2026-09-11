public class Main {
    public static void main(String[] args) {
        Habit habit = new Habit("Morning reading");
        habit.editHabit();
        habit.completeHabit();
        habit.deleteHabit();
    }
}