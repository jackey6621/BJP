package com.revature.P1.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reimbursement")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Reimb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reimbid;

    @Column
    private String description;

    @Column
    private Integer amount;

    @Column
    private String status;

//    @Column
//    private Integer userId;

    @JoinColumn(name = "userid")
    private int userid;

}
