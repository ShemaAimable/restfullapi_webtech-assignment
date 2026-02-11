package com.example.question2_student_api.controller.student;

import com.example.question2_student_api.model.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {

        // 5 Sample Students (as required)
        students.add(new Student(1L, "Alice", "Johnson",
                "alice@email.com", "Computer Science", 3.8));

        students.add(new Student(2L, "Brian", "Smith",
                "brian@email.com", "Information Systems", 3.2));

        students.add(new Student(3L, "Cathy", "Brown",
                "cathy@email.com", "Computer Science", 3.6));

        students.add(new Student(4L, "David", "Wilson",
                "david@email.com", "Business", 2.9));

        students.add(new Student(5L, "Emma", "Davis",
                "emma@email.com", "Software Engineering", 3.9));
    }

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }

        return ResponseEntity.ok(result);
    }

    // GET students with GPA >= minGpa
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }

        return ResponseEntity.ok(result);
    }

    // POST - Register new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        students.add(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // PUT - Update student
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getStudentId().equals(studentId)) {

                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());

                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();

            if (student.getStudentId().equals(studentId)) {
                iterator.remove();
                return ResponseEntity.noContent().build(); // 204
            }
        }

        return ResponseEntity.notFound().build(); // 404
    }
}
