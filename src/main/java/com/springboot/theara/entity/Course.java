package com.springboot.theara.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "courseId")
public class Course {
    @Id
    @SequenceGenerator(name="course_sequence",sequenceName = "course_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")

    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude private CourseMaterial courseMaterial;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id",insertable = false,updatable =false)
    @JsonIgnore
    @ToString.Exclude private Teacher teacher;
    @Column(name="teacher_id")
    private Long teacherId;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch =  FetchType.LAZY)
    @JoinTable( name = "tbl_StudentDetail",
            joinColumns = @JoinColumn(name = "CourseId",referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "StudentID",referencedColumnName = "studentId"))
    @ToString.Exclude private List<Student> students;

    public void addStudent(Student student)
    {
        if(students==null)
            students=new ArrayList<>();
        students.add(student);
    }

}
