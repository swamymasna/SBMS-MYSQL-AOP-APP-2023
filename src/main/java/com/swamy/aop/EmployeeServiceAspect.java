package com.swamy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.swamy.dto.EmployeeDto;

@Aspect
@Component
public class EmployeeServiceAspect {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceAspect.class);

	@Before(value = "execution(* com.swamy.service.impl.EmployeeServiceImpl.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		LOG.info("Entered into " + joinPoint.getSignature() + " method");
	}

	@After(value = "execution(* com.swamy.service.impl.EmployeeServiceImpl.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		LOG.info("Returning Back From " + joinPoint.getSignature() + " method");
	}

	@AfterReturning(value = "execution(* com.swamy.service.impl.EmployeeServiceImpl.saveEmployee(..))", returning = "employeeDto")
	public void afterReturningAdviceForCreateEmployee(JoinPoint joinPoint, EmployeeDto employeeDto) {
		LOG.info(String.format("Employee Saved Successfully With Id : %s", employeeDto.getEmployeeId()));
	}

	@AfterThrowing(value = "execution(* com.swamy.service.impl.EmployeeServiceImpl.saveEmployee(..))", throwing = "exception")
	public void afterThrowingAdviceForCreateEmployee(JoinPoint joinPoint, Exception exception) {
		LOG.info(String.format("Exception Raised While Saving Employee : %s", exception.getMessage()));
	}

	@Around(value = "execution(* com.swamy.service.impl.EmployeeServiceImpl.saveEmployee(..))")
	public EmployeeDto aroundAdviceForCreateEmployee(ProceedingJoinPoint joinPoint) {
		LOG.info("Entered into " + joinPoint.getSignature() + " method");

		try {
			EmployeeDto employeeDto = (EmployeeDto) joinPoint.proceed();
			return employeeDto;
		} catch (Throwable e) {
			LOG.info("Failed To Save Employee.. : " + e.getMessage());
		}

		LOG.info("Returning Back From " + joinPoint.getSignature() + " method");
		return null;
	}
}
