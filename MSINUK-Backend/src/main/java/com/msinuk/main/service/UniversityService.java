package com.msinuk.main.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msinuk.main.model.UniversityDetails;
import com.msinuk.main.model.UniversityDetails_;
import com.msinuk.main.repository.UniversityDetailsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.MapJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UniversityService {


	@Autowired
	private UniversityDetailsRepo universityRepo;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<UniversityDetails> getUniversities() {
		return this.universityRepo.findAllByOrderByRatingDesc();
	}
	public List<UniversityDetails> getWishList(String ids) {
		String[] idArr = ids.split(",");
		List<Long> idList = new ArrayList<>();
		for(String id :idArr) {
			idList.add(Long.parseLong(id));
		}
		return this.universityRepo.findAllById(idList);
	}
	public List<UniversityDetails> addUniversities() throws JsonMappingException, JsonProcessingException {
	
		String jSon ="{\r\n"
				+ "  \"Arts\": [\r\n"
				+ "    \"MA Philosophy and History of Science\",\r\n"
				+ "    \"MA Philosophy\",\r\n"
				+ "    \"MA Philosophy of Biological and Cognitive Sciences\",\r\n"
				+ "    \"MA Composition of Music for Film and Television\",\r\n"
				+ "    \"MA English Literature\",\r\n"
				+ "    \"MA History\",\r\n"
				+ "    \"MA Creative Innovation and Entrepreneurship\",\r\n"
				+ "    \"MSc Social Innovation and Entrepreneurship\",\r\n"
				+ "    \"MA Anthropology\",\r\n"
				+ "    \"MA Logic and Philosophy of Mathematics\",\r\n"
				+ "    \"MA Music\",\r\n"
				+ "    \"MA Film and Television\",\r\n"
				+ "    \"MA Translation\",\r\n"
				+ "    \"MA Religion\",\r\n"
				+ "    \"PG Diploma (Postgraduate Diploma) Philosophy\",\r\n"
				+ "    \"MA Creative Writing\",\r\n"
				+ "    \"MA Philosophy of Physics\",\r\n"
				+ "    \"MA Comparative Literatures and Cultures\",\r\n"
				+ "    \"MA Chinese-English Translation\",\r\n"
				+ "    \"MA Chinese-English Audiovisual Translation\",\r\n"
				+ "    \"MA Environmental Humanities\",\r\n"
				+ "    \"MSc Business Innovation and Entrepreneurship\",\r\n"
				+ "    \"MA Immersive Arts (Virtual and Augmented Reality)\",\r\n"
				+ "    \"MA History of Art\",\r\n"
				+ "    \"MA Medieval Studies\",\r\n"
				+ "    \"MSc Technology Innovation and Entrepreneurship\",\r\n"
				+ "    \"MSc Innovation and Entrepreneurship\",\r\n"
				+ "    \"DSc Black Humanities\"\r\n"
				+ "  ],\r\n"
				+ "  \"Engineering\": [\r\n"
				+ "    \"MSc Robotics\",\r\n"
				+ "    \"MSc Cyber Security (Infrastructures Security)\",\r\n"
				+ "    \"MSc Data Science (Distance Learning)\",\r\n"
				+ "    \"MSc Advanced Composites\",\r\n"
				+ "    \"MSc Biorobotics\",\r\n"
				+ "    \"MSc Digital Health\",\r\n"
				+ "    \"MSc Optical Communications and Signal Processing\",\r\n"
				+ "    \"MSc Communication Networks and Signal Processing\",\r\n"
				+ "    \"MSc Engineering Mathematics\",\r\n"
				+ "    \"MSc Biomedical Engineering\",\r\n"
				+ "    \"MSc Aerial Robotics\",\r\n"
				+ "    \"MSc Financial Technology with Data Science\",\r\n"
				+ "    \"MSc Data Science\",\r\n"
				+ "    \"MSc Immersive Technologies (Virtual and Augmented Reality)\",\r\n"
				+ "    \"MSc Computer Science (Conversion)\",\r\n"
				+ "    \"MSc Optoelectronic and Quantum Technologies\",\r\n"
				+ "    \"MSc Image and Video Communications and Signal Processing\",\r\n"
				+ "    \"MSc Earthquake Engineering and Infrastructure Resilience\",\r\n"
				+ "    \"MSc Water and Environmental Management\",\r\n"
				+ "    \"MSc Advanced Microelectronic Systems Engineering\",\r\n"
				+ "    \"MSc Engineering with Management\",\r\n"
				+ "    \"MSc Wireless Communications and Signal Processing\"\r\n"
				+ "  ],\r\n"
				+ "  \"Health Sciences\": [\r\n"
				+ "    \"MRes Health Sciences Research\",\r\n"
				+ "    \"MSc Oral Medicine\",\r\n"
				+ "    \"MSc Perfusion Science\",\r\n"
				+ "    \"PG Certificate (Postgraduate Certificate) Perfusion Science\",\r\n"
				+ "    \"MSc Teaching and Learning for Health Professionals\",\r\n"
				+ "    \"MSc Public Health\",\r\n"
				+ "    \"MSc Molecular Neuroscience\",\r\n"
				+ "    \"MSc Health Economics and Health Policy Analysis\",\r\n"
				+ "    \"PG Certificate (Postgraduate Certificate) Healthcare Improvement\",\r\n"
				+ "    \"MSc Medical Statistics and Health Data Science\",\r\n"
				+ "    \"PG Certificate (Postgraduate Certificate) Clinical Oral Surgery\",\r\n"
				+ "    \"MSc Dental Implantology\"\r\n"
				+ "  ],\r\n"
				+ "  \"Life Sciences\": [\r\n"
				+ "    \"PG Diploma (Postgraduate Diploma) Theoretical and Practical Clinical Neuropsychology\",\r\n"
				+ "    \"PG Diploma (Postgraduate Diploma) Applied Neuropsychology (Bristol-based)\",\r\n"
				+ "    \"MSc Science Communication for a Better Planet\",\r\n"
				+ "    \"PG Diploma (Postgraduate Diploma) Applied Neuropsychology (distance learning)\",\r\n"
				+ "    \"PG Certificate (Postgraduate Certificate) Clinical Neuropsychology Practice\",\r\n"
				+ "    \"MSc Biomedical Sciences Research\",\r\n"
				+ "    \"MSc Applied Neuropsychology (Distance Learning)\",\r\n"
				+ "    \"PG Diploma (Postgraduate Diploma) Clinical Neuropsychology\",\r\n"
				+ "    \"MSc Applied Neuropsychology\"\r\n"
				+ "  ],\r\n"
				+ "  \"Science\": [\r\n"
				+ "    \"MSc Mathematical Sciences\",\r\n"
				+ "    \"MSc Environmental Policy and Management\",\r\n"
				+ "    \"MSc Climate Change Science and Policy\",\r\n"
				+ "    \"MSc Volcanology\",\r\n"
				+ "    \"MSc Global Development and Environment\",\r\n"
				+ "    \"MSc Geographic Data Science and Spatial Analytics\",\r\n"
				+ "    \"MSc Nanoscience and Functional Nanomaterials\",\r\n"
				+ "    \"MSc Nuclear Science and Engineering\",\r\n"
				+ "    \"MSc Scientific Computing with Data Science\",\r\n"
				+ "    \"MSc Environmental Modelling and Data Analysis\"\r\n"
				+ "  ],\r\n"
				+ "  \"Social Sciences and Law\": [\r\n"
				+ "    \"LLM Banking and Finance Law\",\r\n"
				+ "    \"LLM Labour Law and Corporate Governance\",\r\n"
				+ "    \"LLM International Law\",\r\n"
				+ "    \"LLM Health, Law and Society\",\r\n"
				+ "    \"MSc Social Science Research Methods (Politics)\"\r\n"
				+ "  ]\r\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    @SuppressWarnings("unchecked")
	    Map <String, String[]> courses = mapper.readValue(jSon, new TypeReference<Map<String,String[]>>(){});
		String description= "The University of Bristol is a red brick Russell Group research university in Bristol, England. It received its royal charter in 1909, although it can trace its roots to a Merchant Venturers' school founded in 1595 and University College, Bristol, which had been in existence since 1876."
				+ "Bristol is a member of the Russell Group of research-intensive British universities, the European-wide Coimbra Group and the Worldwide Universities Network, of which the university's previous vice-chancellor, Eric Thomas, was chairman from 2005 to 2007.";
		String[] images = {"UniversityofBristol.jpg","UniversityofBristol1.jpg","UniversityofBristol2.jpg","UniversityofBristol3.jpg"};
		String reviews = "https://www.google.com/maps/place/University+of+Bristol/@51.4585376,-2.6047507,17z/data=!4m8!3m7!1s0x48718ddbdfd292fb:0x2f0b60f89b4b6d56!8m2!3d51.4585376!4d-2.6021758!9m1!1b1!16zL20vMGdrN3o?entry=ttu";
		String courseMap = "{\r\n"
				+ "  \"commonurl\": \"https://www.bristol.ac.uk/study/postgraduate/2023/\",\r\n"
				+ "  \"MA Philosophy and History of Science\": \"arts/ma-philosophy-and-history-of-science/\",\r\n"
				+ "  \"MA Philosophy\": \"arts/ma-philosophy/\",\r\n"
				+ "  \"MA Philosophy of Biological and Cognitive Sciences\": \"arts/ma-philosophy-of-biological-and-cognitive-sciences/\",\r\n"
				+ "  \"MA Composition of Music for Film and Television\": \"arts/ma-composition-of-music-for-film-and-television/\",\r\n"
				+ "  \"MA English Literature\": \"arts/ma-english-literature/\",\r\n"
				+ "  \"MA History\": \"arts/ma-history/\",\r\n"
				+ "  \"MA Creative Innovation and Entrepreneurship\": \"arts/ma-creative-innovation-and-entrepreneurship/\",\r\n"
				+ "  \"MSc Social Innovation and Entrepreneurship\": \"arts/msc-social-innovation-and-entrepreneurship/\",\r\n"
				+ "  \"MA Anthropology\": \"arts/ma-anthropology/\",\r\n"
				+ "  \"MA Logic and Philosophy of\": \"arts/ma-logic-and-philosophy-of-mathematics/\",\r\n"
				+ "  \"MA Music\": \"arts/ma-music/\",\r\n"
				+ "  \"MA Film and Television\": \"arts/ma-film-and-television/\",\r\n"
				+ "  \"MA Translation\": \"arts/ma-translation/\",\r\n"
				+ "  \"MA Religion\": \"arts/ma-religion/\",\r\n"
				+ "  \"PG Diploma (Postgraduate Diploma) Philosophy\": \"arts/pg-diploma-postgraduate-diploma-philosophy/\",\r\n"
				+ "  \"MA Creative Writing\": \"arts/ma-creative-writing/\",\r\n"
				+ "  \"MA Philosophy of Physics\": \"arts/ma-philosophy-of-physics/\",\r\n"
				+ "  \"MA Comparative Literatures and Cultures\": \"arts/ma-comparative-literatures-and-cultures/\",\r\n"
				+ "  \"MA Chinese-English Translation\": \"arts/ma-chinese-english-translation/\",\r\n"
				+ "  \"MA Environmental Humanities\": \"arts/ma-environmental-humanities/\",\r\n"
				+ "  \"MSc Business Innovation and Entrepreneurship\": \"arts/msc-business-innovation-and-entrepreneurship/\",\r\n"
				+ "  \"MA Immersive Arts (Virtual and Augmented Reality)\": \"arts/ma-immersive-arts-virtual-and-augmented-reality/\",\r\n"
				+ "  \"MA History of Art\": \"arts/ma-history-of-art/\",\r\n"
				+ "  \"MA Medieval Studies\": \"arts/ma-medieval-studies/\",\r\n"
				+ "  \"MSc Technology Innovation and Entrepreneurship\": \"arts/msc-technology-innovation-and-entrepreneurship/\",\r\n"
				+ "  \"MSc Innovation and Entrepreneurship\": \"arts/msc-innovation-and-entrepreneurship/\",\r\n"
				+ "  \"DSc Black Humanities\": \"arts/ma-black-humanities/\",\r\n"
				+ "  \"MSc Robotics\": \"eng/msc-robotics/\",\r\n"
				+ "  \"MSc Cyber Security (Infrastructures Security)\": \"eng/msc-cyber-security-infrastructures-security/\",\r\n"
				+ "  \"MSc Data Science (Distance Learning)\": \"eng/msc-data-science-distance-learning/\",\r\n"
				+ "  \"MSc Advanced Composites\": \"eng/msc-advanced-composites/\",\r\n"
				+ "  \"MSc Biorobotics\": \"eng/msc-biorobotics/\",\r\n"
				+ "  \"MSc Digital Health\": \"eng/msc-digital-health/\",\r\n"
				+ "  \"MSc Optical Communications and Signal Processing\": \"eng/msc-optical-communications-and-signal-processing/\",\r\n"
				+ "  \"MSc Communication Networks and Signal Processing\": \"eng/msc-networks-and-signal-processing/\",\r\n"
				+ "  \"MSc Engineering Mathematics\": \"eng/msc-engineering-mathematics/\",\r\n"
				+ "  \"MSc Biomedical Engineering\": \"eng/msc-biomedical-engineering/\",\r\n"
				+ "  \"MSc Aerial Robotics\": \"eng/msc-aerial-robotics/\",\r\n"
				+ "  \"MSc Financial Technology with Data Science\": \"eng/msc-financial-technology-with-data-science/\",\r\n"
				+ "  \"MSc Data Science\": \"eng/msc-data-science/\",\r\n"
				+ "  \"MSc Immersive Technologies (Virtual and Augmented Reality)\": \"eng/msc-immersive-technologies-virtual-and-augmented-reality/\",\r\n"
				+ "  \"MSc Computer Science (Conversion)\": \"eng/msc-computer-science-conversion/\",\r\n"
				+ "  \"MSc Optoelectronic and Quantum Technologies\": \"eng/msc-optoelectronic-and-quantum-technologies/\",\r\n"
				+ "  \"MSc Image and Video Communications and Signal Processing\": \"eng/msc-image-and-video-communications-and-signal-processing/\",\r\n"
				+ "  \"MSc Earthquake Engineering and Infrastructure Resilience\": \"eng/msc-earthquake-engineering-and-infrastructure-resilience/\",\r\n"
				+ "  \"MSc Water and Environmental Management\": \"eng/msc-water-and-environmental-management/\",\r\n"
				+ "  \"MSc Advanced Microelectronic Systems Engineering\": \"eng/msc-advanced-microelectronic-systems-engineering/\",\r\n"
				+ "  \"MSc Engineering with Management\": \"eng/msc-engineering-with-management/\",\r\n"
				+ "  \"MSc Wireless Communications and Signal Processing\": \"eng/msc-wireless-communications-and-signal-processing/\",\r\n"
				+ "  \"MRes Health Sciences Research\": \"health-sciences/mres-health-sciences-research/\",\r\n"
				+ "  \"MSc Oral Medicine\": \"health-sciences/msc-oral-medicine/\",\r\n"
				+ "  \"MSc Perfusion Science\": \"health-sciences/msc-perfusion-science/\",\r\n"
				+ "  \"MSc Orthopaedic Research Methods and Evidence-Based Medicine\": \"health-sciences/msc-orthopaedic-research-methods-and-evidence-based-medicine/\",\r\n"
				+ "  \"PG Certificate (Postgraduate Certificate) Perfusion Science\": \"health-sciences/pg-certificate-postgraduate-certificate-perfusion-science/\",\r\n"
				+ "  \"MSc Teaching and Learning for Health Professionals\": \"health-sciences/msc-teaching-and-learning-for-health-professionals/\",\r\n"
				+ "  \"MSc Public Health\": \"health-sciences/msc-public-health/\",\r\n"
				+ "  \"MSc Molecular Neuroscience\": \"health-sciences/msc-molecular-neuroscience/\",\r\n"
				+ "  \"MSc Health Economics and Health Policy Analysis\": \"health-sciences/msc-health-economics-and-health-policy-analysis/\",\r\n"
				+ "  \"PG Certificate (Postgraduate Certificate) Healthcare Improvement\": \"health-sciences/pg-certificate-postgraduate-certificate-healthcare-improvement/\",\r\n"
				+ "  \"MSc Medical Statistics and Health Data Science\": \"health-sciences/msc-medical-statistics-and-health-data-science/\",\r\n"
				+ "  \"PG Certificate (Postgraduate Certificate) Clinical Oral Surgery\": \"health-sciences/pg-certificate-postgraduate-certificate-clinical-oral-surgery\",\r\n"
				+ "  \"MSc Dental Implantology\": \"health-sciences/msc-dental-implantology/\",\r\n"
				+ "  \"PG Diploma (Postgraduate Diploma) Theoretical and Practical Clinical Neuropsychology\": \"life-sciences/pg-diploma-postgraduate-diploma-theoretical-and-practical-clinical-neuropsychology/\",\r\n"
				+ "  \"PG Diploma (Postgraduate Diploma) Applied Neuropsychology (Bristol-based)\": \"life-sciences/pg-diploma-postgraduate-diploma-applied-neuropsychology-bristol-based/\",\r\n"
				+ "  \"MSc Science Communication for a Better Planet\": \"life-sciences/msc-science-communication-for-a-better-planet/\",\r\n"
				+ "  \"PG Diploma (Postgraduate Diploma) Applied Neuropsychology (distance learning)\": \"life-sciences/pg-diploma-postgraduate-diploma-applied-neuropsychology-distance-learning/\",\r\n"
				+ "  \"PG Certificate (Postgraduate Certificate) Clinical Neuropsychology Practice\": \"life-sciences/pg-certificate-postgraduate-certificate-clinical-neuropsychology-practice/\",\r\n"
				+ "  \"MSc Biomedical Sciences Research\": \"life-sciences/msc-biomedical-sciences-research/\",\r\n"
				+ "  \"MSc Applied Neuropsychology (Distance Learning)\": \"life-sciences/msc-applied-neuropsychology-distance-learning/\",\r\n"
				+ "  \"PG Diploma (Postgraduate Diploma) Clinical Neuropsychology\": \"life-sciences/pg-diploma-postgraduate-diploma-clinical-neuropsychology/\",\r\n"
				+ "  \"MSc Applied Neuropsychology\": \"life-sciences/msc-applied-neuropsychology/\",\r\n"
				+ "  \"MSc Mathematical Sciences\": \"sci/msc-mathematical-sciences/\",\r\n"
				+ "  \"MSc Environmental Policy and Management\": \"sci/msc-environmental-policy-and-management/\",\r\n"
				+ "  \"MSc Climate Change Science and Policy\": \"sci/msc-climate-change-science-and-policy/\",\r\n"
				+ "  \"MSc Volcanology\": \"sci/msc-volcanology/\",\r\n"
				+ "  \"MSc Global Development and Environment\": \"sci/msc-global-development-and-environment/\",\r\n"
				+ "  \"MSc Geographic Data Science and Spatial Analytics\": \"sci/msc-geographic-data-science-and-spatial-analytics/\",\r\n"
				+ "  \"MSc Nanoscience and Functional Nanomaterials\": \"sci/msc-nanoscience-and-functional-nanomaterials/\",\r\n"
				+ "  \"MSc Nuclear Science and Engineering\": \"sci/msc-nuclear-science-and-engineering/\",\r\n"
				+ "  \"MSc Scientific Computing with Data Science\": \"sci/msc-scientific-computing-with-data-science/\",\r\n"
				+ "  \"MSc Environmental Modelling and Data Analysis\": \"sci/msc-environmental-modelling-and-data-analysis/\",\r\n"
				+ "  \"LLM Banking and Finance Law\": \"ssl/llm-law---banking-and-finance-law/\",\r\n"
				+ "  \"LLM Labour Law and Corporate Governance\": \"ssl/llm-law---labour-law-and-corporate-governance/\",\r\n"
				+ "  \"LLM International Law\": \"ssl/llm-law---international-law/\",\r\n"
				+ "  \"LLM Health, Law and Society\": \"ssl/llm-law---health-law-and-society/\",\r\n"
				+ "  \"MSc Social Science Research Methods (Politics)\": \"ssl/msc-social-science-research-methods-politics/\"\r\n"
				+ "}";
		UniversityDetails undetails = new UniversityDetails(16, "University of Bristol", "Beacon House, Queens Rd, Bristol BS8 1QU", courses, 4.4, description, images, "+44 (0)117 928 9000.", reviews,courseMap);
				
		this.universityRepo.save(undetails);
	
	return this.universityRepo.findAllByOrderByRatingDesc();
	}
	public List<UniversityDetails> getUniversitiesByName(String universityName, String courseName, String department) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UniversityDetails> query = cb.createQuery(UniversityDetails.class);
        Root<UniversityDetails> university = query.from(UniversityDetails.class);
        
        MapJoin<UniversityDetails, String, String[]> courseJoin = university.join(UniversityDetails_.courses, JoinType.INNER);
        Path<String> departmentPath= courseJoin.key();
        Expression<String> coursePath= courseJoin.value().as(String.class);
        List<Predicate> predicates = new ArrayList<>();
        if(!universityName.equalsIgnoreCase("undefined")) {
        	Path<String> uName = university.get("universityName");
        	predicates.add(cb.like(uName, "%"+universityName+"%"));
        }
        if(!courseName.equalsIgnoreCase("undefined")) {
        	predicates.add(cb.like(coursePath, "%"+courseName+"%"));
        }
        if (!department.equalsIgnoreCase("undefined")) {
        	predicates.add(cb.like(departmentPath, "%"+department+"%"));
        }
        query.select(university).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        query.orderBy(cb.desc(university.get("rating")));

        return entityManager.createQuery(query).getResultList();
	}
	
	public Optional<UniversityDetails> getUniversityById(String unId) {
		return this.universityRepo.findById(Long.parseLong(unId));
		
	}

	
}
