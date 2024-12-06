package com.example.demo.apipayload.status;

import com.example.demo.apipayload.ErrorReasonDTO;

public interface BaseErrorCode {

    public ErrorReasonDTO getReason();

    public ErrorReasonDTO getReasonHttpStatus();

}
