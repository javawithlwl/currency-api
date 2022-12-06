package com.lwl.currency.batch;

import com.lwl.currency.domain.Employee;
import com.lwl.currency.repo.EmployeeRepo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration
public class BatchConfiguration {
  @Autowired
  private EmployeeRepo employeeRepo;
  @Bean
  public FlatFileItemReader<EmployeeDto> itemReader(){
      FlatFileItemReader<EmployeeDto> reader = new FlatFileItemReader<>();
      reader.setResource(new ClassPathResource("/employee.csv"));
      reader.setLinesToSkip(1);
      reader.setLineMapper(lineMapper());
      return reader;
  }
  @Bean
  public CustomItemProcessor processor(){
    return new CustomItemProcessor();
  }
  @Bean
  public RepositoryItemWriter<Employee> itemWriter(){
      RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
      writer.setRepository(employeeRepo);
      writer.setMethodName("save");
      return writer;
  }
  @Bean
  public Job sampleJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
    return new JobBuilder("sampleJob", jobRepository)
        .start(step1(jobRepository,transactionManager))
        .build();
  }
  @Bean
  public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager){
    return new StepBuilder("step",jobRepository)
        .<EmployeeDto, Employee>chunk(50,transactionManager)
        .reader(itemReader())
        .processor(processor())
        .writer(itemWriter())
        .build();
  }
  private LineMapper<EmployeeDto> lineMapper() {
    DefaultLineMapper<EmployeeDto> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames("id","firstName","lastName","email","gender","salary");
    BeanWrapperFieldSetMapper<EmployeeDto> setMapper = new BeanWrapperFieldSetMapper();
    setMapper.setTargetType(EmployeeDto.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(setMapper);
    return lineMapper;
  }

}
