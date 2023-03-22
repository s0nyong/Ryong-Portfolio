package com.bowwow.customer.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class KakaoProperties {

	private Long id;
	private String connectedAt;
	private Properties properties;
	private Kakao_account kakao_account;
	
	public KakaoProperties() {
	}

	public KakaoProperties(Long id, String connectedAt, Properties properties, Kakao_account kakao_account) {
		this.id = id;
		this.connectedAt = connectedAt;
		this.properties = properties;
		this.kakao_account = kakao_account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConnectedAt() {
		return connectedAt;
	}

	public void setConnectedAt(String connectedAt) {
		this.connectedAt = connectedAt;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Kakao_account getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(Kakao_account kakao_account) {
		this.kakao_account = kakao_account;
	}
	
//===================Properties====================
	public class Properties {

		private String nickname;

		public Properties() {
		}

		public Properties(String nickname) {
			super();
			this.nickname = nickname;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Properties withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

	}

	
//===================Kakao_account====================	
	@JsonIgnoreProperties(ignoreUnknown=true)
	public class Kakao_account {

		private Profile profile;
		private String email;

		public Kakao_account() {
		}
		
		public Kakao_account(Profile profile, String email) {
			this.profile = profile;
			this.email = email;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		//-------------------------------Profile---------------------------
		@JsonIgnoreProperties(ignoreUnknown=true)
		public class Profile {

			private String nickname;

			public Profile() {
			}

			public Profile(String nickname) {
				this.nickname = nickname;
			}

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public Profile withNickname(String nickname) {
				this.nickname = nickname;
				return this;
			}

		}

	}
}










