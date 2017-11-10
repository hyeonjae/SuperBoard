package com.nowpark.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Entity
public class Post {

	@Transient
	private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String contents;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date createdAt;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date updatedAt;

	private Boolean deletedYn;

	@OneToOne
	private User author;
}
