package Practice;


import java.util.*;

public class Q1 {
    interface Question {
        boolean checkAnswer(String answer);
    }

    static class MCQQuestion implements Question {
        int number;
        String correctAnswer;

        MCQQuestion(int number, String correctAnswer) {
            this.number = number;
            this.correctAnswer = correctAnswer;
        }

        public boolean checkAnswer(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class Attempt {
        Examination exam;
        Map<Question, String> answers = new LinkedHashMap<>();
        boolean submitted = false;
        int score = 0;

        Attempt(Examination exam) {
            this.exam = exam;
        }

        void answer(Question q, String answer) {
            if (submitted) {
                System.out.println("Cannot change submitted answers.");
                return;
            }
            if (!exam.questions.contains(q)) {
                System.out.println("Question does not belong to this exam.");
                return;
            }
            answers.put(q, answer);
            System.out.println("Question answered with '" + answer + "'.");
        }

        void submit() {
            if (submitted) {
                System.out.println("Attempt already submitted.");
                return;
            }

            for (Question q : exam.questions) {
                if (q.checkAnswer(answers.getOrDefault(q, ""))) {
                    score++;
                }
            }

            submitted = true;
            System.out.println("Examination '" + exam.name
                    + "' submitted successfully.");
            System.out.println("Result for '" + exam.name
                    + "' attempt: " + score + "/"
                    + exam.questions.size() + " correct.");
        }
    }

    static class Examination {
        String name;
        List<Question> questions = new ArrayList<>();
        Map<Student, Attempt> attempts = new HashMap<>();

        Examination(String name) {
            this.name = name;
        }

        Attempt start(Student student) {
            Attempt old = attempts.get(student);

            if (old != null && old.submitted) {
                System.out.println("A submitted attempt already exists.");
                return null;
            }

            if (old != null) {
                return old;
            }

            Attempt a = new Attempt(this);
            attempts.put(student, a);
            System.out.println("Examination '" + name
                    + "' started by " + student.name + ".");
            return a;
        }
    }

    static class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Student s = new Student("Student");
        Examination e = new Examination("Math Quiz");

        Question q1 = new MCQQuestion(1, "A");
        Question q2 = new MCQQuestion(2, "B");

        e.questions.add(q1);
        e.questions.add(q2);

        Attempt a = e.start(s);
        if (a != null) {
            a.answer(q1, "A");
            a.answer(q2, "C");
            a.submit();
        }
    }
}

