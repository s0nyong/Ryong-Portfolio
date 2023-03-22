package com.bowwow.admin.notice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bowwow.common.entity.Notice;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository repo;
	
//	@Test
//	public void testCreateNotice() {
//		Notice firstNotice = new Notice("공지", "사항");
//		Notice savedNotice = repo.save(firstNotice);
//		
//		assertThat(savedNotice.getId()).isGreaterThan(0);
//	}
}
