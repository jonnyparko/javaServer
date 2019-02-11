package com.jonnyparko.web.webSite;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Car {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
    
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public Long getId() {
		return this.id;
	}
}