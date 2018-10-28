
package com.swipejobs.file.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "driverLicenseRequired",
    "requiredCertificates",
    "location",
    "billRate",
    "workersRequired",
    "startDate",
    "about",
    "jobTitle",
    "company",
    "guid",
    "jobId"
})
public class Job {

    @JsonProperty("driverLicenseRequired")
    private Boolean driverLicenseRequired;
    @JsonProperty("requiredCertificates")
    private List<String> requiredCertificates = null;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("billRate")
    private String billRate;
    @JsonProperty("workersRequired")
    private Integer workersRequired;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("about")
    private String about;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("company")
    private String company;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("jobId")
    private Integer jobId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Job() {
    }

    /**
     * 
     * @param guid
     * @param driverLicenseRequired
     * @param startDate
     * @param billRate
     * @param requiredCertificates
     * @param jobId
     * @param location
     * @param company
     * @param about
     * @param workersRequired
     * @param jobTitle
     */
    public Job(Boolean driverLicenseRequired, List<String> requiredCertificates, Location location, String billRate, Integer workersRequired, String startDate, String about, String jobTitle, String company, String guid, Integer jobId) {
        super();
        this.driverLicenseRequired = driverLicenseRequired;
        this.requiredCertificates = requiredCertificates;
        this.location = location;
        this.billRate = billRate;
        this.workersRequired = workersRequired;
        this.startDate = startDate;
        this.about = about;
        this.jobTitle = jobTitle;
        this.company = company;
        this.guid = guid;
        this.jobId = jobId;
    }

    @JsonProperty("driverLicenseRequired")
    public Boolean getDriverLicenseRequired() {
        return driverLicenseRequired;
    }

    @JsonProperty("driverLicenseRequired")
    public void setDriverLicenseRequired(Boolean driverLicenseRequired) {
        this.driverLicenseRequired = driverLicenseRequired;
    }

    @JsonProperty("requiredCertificates")
    public List<String> getRequiredCertificates() {
        return requiredCertificates;
    }

    @JsonProperty("requiredCertificates")
    public void setRequiredCertificates(List<String> requiredCertificates) {
        this.requiredCertificates = requiredCertificates;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("billRate")
    public String getBillRate() {
        return billRate;
    }

    @JsonProperty("billRate")
    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    @JsonProperty("workersRequired")
    public Integer getWorkersRequired() {
        return workersRequired;
    }

    @JsonProperty("workersRequired")
    public void setWorkersRequired(Integer workersRequired) {
        this.workersRequired = workersRequired;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("about")
    public String getAbout() {
        return about;
    }

    @JsonProperty("about")
    public void setAbout(String about) {
        this.about = about;
    }

    @JsonProperty("jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    @JsonProperty("jobTitle")
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("jobId")
    public Integer getJobId() {
        return jobId;
    }

    @JsonProperty("jobId")
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
