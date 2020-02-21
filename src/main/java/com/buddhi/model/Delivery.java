package com.buddhi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pickup_name")
    private String pickupName;
    @Column(name = "pickup_address")
    private String pickupAddress;
    @Column(name = "pickup_datetime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pickupDateTime;
    @Column(name = "pickup_contact_numbers")
    private String pickupContactNumbers;
    @Column(name = "pickup_comment")
    private String pickupComment;
    @Column(name = "drop_name")
    private String dropName;
    @Column(name = "drop_address")
    private String dropAddress;
    @Column(name = "drop_contact_numbers")
    private String dropContactNumbers;
    @Column(name = "drop_comment")
    private String dropComment;
}
