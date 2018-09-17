package com.MinicareStruts.dao;

import com.MinicareStruts.dto.JobApplicationDTO;
import com.MinicareStruts.dto.ListApplicationDTO;
import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.JobApplication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

    /*
    public void applyJob(JobApplication application) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(application);
        transaction.commit();
    }
    */

    public void applyJob(JobApplication application) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into jobapplication (JobId, SitterId, ExpectedPay) values (?,?,?)");
            pst.setInt(1,application.getJobId());
            pst.setInt(2,application.getSitterId());
            pst.setDouble(3,application.getExpectedPay());
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
    }

    /*
    public List<JobApplicationDTO> listApplications(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();


    }
    */

        public List<JobApplicationDTO> listApplications(int sitterId) {
        List<JobApplicationDTO> list = new ArrayList<JobApplicationDTO>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select job.Title, jobapplication.ExpectedPay, job.PayPerHour, job.Status, " +
                    "job.JobId, jobapplication.ApplicationId, job.SeekerId from job inner join jobapplication on job.JobId=jobapplication.JobId where " +
                    "jobapplication.SitterId=? and jobapplication.Status=?");
            pst.setInt(1,sitterId);
            pst.setString(2,"ACTIVE");
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                JobApplicationDTO jobApplicationDto = new JobApplicationDTO();
                jobApplicationDto.setTitle(resultSet.getString(1));
                jobApplicationDto.setExpectedPay(resultSet.getDouble(2));
                jobApplicationDto.setPayPerHour(resultSet.getDouble(3));
                jobApplicationDto.setStatus(JobApplicationDTO.Status.valueOf(resultSet.getString(4)));
                jobApplicationDto.setJobId(resultSet.getInt(5));
                jobApplicationDto.setApplicationId(resultSet.getInt(6));
                jobApplicationDto.setSeekerId(resultSet.getInt(7));

                list.add(jobApplicationDto);
            }

        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }

    /*
    public void deleteApplication(int applicationId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        JobApplication jobApplication = (JobApplication)session.get(JobApplication.class,applicationId);
        //So here there is Status field in the table of jobapplication but i forgot to add that in the
        //JobApplication Model class. So First Add that and then do the Mapping and then Validate the mapping.
        jobApplication.setStatus(JobApplication.Status.INACTIVE);
        session.saveOrUpdate(jobApplication);
        transaction.commit();
    }
    */

    public void deleteApplication(int applicationId) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status = ? where ApplicationId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,applicationId);
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
    }

    /*
    public void deleteApplicationsById(int jobId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE JobApplication set status=? where jobId=?");
        query.setString(0,"INACTIVE");
        query.setInteger(1,jobId);
        query.executeUpdate();
        transaction.commit();
    }
    */

        public boolean deleteApplicationsById(int jobId) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status = ? where JobId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,jobId);
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    /*
    public void deleteApplicationsBySitterId(int sitterId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE JobApplication set status=? where sitterId=?");
        query.setString(0,"INACTIVE");
        query.setInteger(1,sitterId);
        query.executeUpdate();
        transaction.commit();
    }
    */

    public boolean deleteApplicationsBySitterId(int sitterId) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status=? where SitterId=?");
            pst.setString(1, "INACTIVE");
            pst.setInt(2, sitterId);
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    /*
    public List<ListApplicationDTO> listApplicationsForSeeker1(int jobId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        //So here if i write Just a select query for Job which would have Set of JobApplications and seeker Reference
        //Then how to specify that select only the job applications which are Active ?
        //So think of some query and then implement this.
        //select * from job from Job inner join JobApplication on Job.jobId=JobApplication.jobId where
        //JobApplication.status="ACTIVE" - Check this


    }
    */

    public List<ListApplicationDTO> listApplicationsForSeeker(int jobId) {
        List<ListApplicationDTO> list = new ArrayList<ListApplicationDTO>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select member.FirstName, jobapplication.Status, jobapplication.ExpectedPay, " +
                    "job.Title, jobapplication.SitterId from jobapplication inner join job on jobapplication.JobId=job.JobId inner join member " +
                    "on jobapplication.SitterId=member.MemberId where jobapplication.JobId=? and jobapplication.Status=?");
            pst.setInt(1,jobId);
            pst.setString(2,"ACTIVE");
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                ListApplicationDTO listApplication = new ListApplicationDTO();
                listApplication.setFirstName(resultSet.getString(1));
                listApplication.setStatus(ListApplicationDTO.Status.valueOf(resultSet.getString(2)));
                listApplication.setExpectedPay(resultSet.getDouble(3));
                listApplication.setTitle(resultSet.getString(4));
                listApplication.setSitterId(resultSet.getInt(5));

                list.add(listApplication);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }
}
