package com.pongi.project.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//dto의 @getter가 잘 작동하는지 확인
public class HelloResponseDtoTest {

    @Test
    public void lombok_function_test(){
        //given
        String name="test";
        int amount= 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
