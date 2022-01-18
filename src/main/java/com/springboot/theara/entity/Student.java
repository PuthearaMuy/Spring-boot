package com.springboot.theara.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",uniqueConstraints = @UniqueConstraint(name = "emailId",columnNames = "emailId"))
public class Student {
    
	@Id
    @SequenceGenerator(name="student_sequence",sequenceName = "student_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_sequence")
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name="emailId",nullable = false)
    private String emailId;
    @Embedded
    private Guardian guardian;
    @ManyToMany(mappedBy = "students",cascade = CascadeType.ALL)
    private List<Course> courses;
    public void addCourse(Course course)
    {
        if(courses==null) courses=new ArrayList<>();
        courses.add(course);
    }
}
