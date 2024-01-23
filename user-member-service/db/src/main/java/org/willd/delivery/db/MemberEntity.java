package org.willd.delivery.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEntity {
    @Id
    private Long id;



}
