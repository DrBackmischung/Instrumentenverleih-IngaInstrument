package de.mathisneunzig.ingainstrument.Chatbot.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name="profile")
public class UserProfile {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private String ip;
	
	@Column
	@NotNull
	private int gitarre;
	
	@Column
	@NotNull
	private int schlagzeug;
	
	@Column
	@NotNull
	private int orgel;

	public UserProfile(@NotNull String ip) {
		super();
		this.ip = ip;
		this.gitarre = 0;
		this.orgel = 0;
		this.schlagzeug = 0;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getGitarre() {
		return gitarre;
	}

	public void setGitarre(int gitarre) {
		this.gitarre = gitarre;
	}

	public int getSchlagzeug() {
		return schlagzeug;
	}

	public void setSchlagzeug(int schlagzeug) {
		this.schlagzeug = schlagzeug;
	}

	public int getOrgel() {
		return orgel;
	}

	public void setOrgel(int orgel) {
		this.orgel = orgel;
	}
	
	public String getHighest() {
		if(getOrgel() >= getSchlagzeug()) {
			if(getGitarre() > getOrgel()) {
				return "G";
			} else 
				return "O";
		} else {
			if(getGitarre() > getSchlagzeug()) {
				return "G";
			} else 
				return "S";
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(gitarre, id, ip, orgel, schlagzeug);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		return gitarre == other.gitarre && Objects.equals(id, other.id) && Objects.equals(ip, other.ip)
				&& orgel == other.orgel && schlagzeug == other.schlagzeug;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", ip=" + ip + ", gitarre=" + gitarre + ", schlagzeug=" + schlagzeug
				+ ", orgel=" + orgel + "]";
	}
	
}
