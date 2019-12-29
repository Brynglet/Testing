package com.annotationz2;

@CustomTypeAnno(priority = CustomTypeAnno.Priority.HIGH, tags = {"tag1","tag2"}, createdBy = "jb")
public class AnnotationExample {

	@CustomMethodAnno
	public String shouldAlwaysProcessed() {
		return "Always Processed";
	}

	@CustomMethodAnno
	public void willThrowE() {
		throw new RuntimeException("Throw exc! RuntExc...");
	}

	@CustomMethodAnno(enabled = false)
	public void shouldNeverProcessed() {
		throw new RuntimeException("Never Processed. RuntExc...");
	}

}