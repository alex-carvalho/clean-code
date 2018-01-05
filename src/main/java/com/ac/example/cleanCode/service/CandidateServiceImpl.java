package com.ac.example.cleanCode.service;

import com.ac.example.cleanCode.model.Candidate;
import com.ac.example.cleanCode.model.Course;
import com.ac.example.cleanCode.repository.CandidateRepository;
import com.ac.example.cleanCode.utis.WebBrowser;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alex Carvalho
 */
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    /**
     * Registra um candidato
     *
     * @param candidate
     * @return
     */
    @Override
    public int register(Candidate candidate) {
        //inicialicacao das variaveis
        Integer candidateId = null;
        boolean good = false;
        boolean appr = false;
        //var nt = new List<String> {"MVC4", "Node.js", "CouchDB", "KendoUI", "Dapper", "Angular"};
        List<String> ot = Arrays.asList("Cobol", "Punch Cards", "Commodore", "VBScript");

        //ISSUE #5274 12/10/2012
        // Não estava sendo filtrado o dominio então adicionei
        List<String> domains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "CompuServe.com");

        if (candidate.getFirstName() != null && !candidate.getFirstName().isEmpty()) {
            if (candidate.getLastName() != null && !candidate.getLastName().isEmpty()) {


                if (candidate.getEmail() != null && !candidate.getEmail().isEmpty()) {
                    //adiciona as empresas na lista
                    List<String> emps = Arrays.asList("Microsoft", "Google", "Fog Creek Software", "37Signals");

                    //ISSUE #838 Jimmy
                    //Antes o  numero e certificacoes preciso era 2 agora e 3
                    good = ((candidate.getExp() > 10 || candidate.isHasBlog() || candidate.getCertifications().size() > 3 || emps.contains(candidate.getEmployer())));


                    if (!good) {
                        //Split para pegar apenas o dominio
                        String emailDomain = candidate.getEmail().split("@")[1];

                        if (!domains.contains(emailDomain) && (!(candidate.getBrowser().getName() == WebBrowser.BrowserName.INTERNET_EXPLORER && candidate.getBrowser().getMajorVersion() < 9))) {
                            good = true;
                        }
                    }

                    if (good) {
                        //ISSUE #5013 1/12/2012
                        //Agora precisa ter pelo menos um um curso
                        if (candidate.getCourseList().size() != 0) {
                            for (Course course : candidate.getCourseList()) {
                                //for (ourse course : candidate.getCourseList())
                                //{
                                //    if (course.getTitle().contains(tech))
                                //    {
                                //        course.setApproved(true);
                                //        break;
                                //    }
                                //}

                                for (String tech : ot) {
                                    if (course.getTitle().contains(tech) || course.getDescription().contains(tech)) {
                                        course.setApproved(false);
                                        break;
                                    } else {
                                        course.setApproved(true);
                                        appr = true;
                                    }
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Can't register candidate with no couse to present.");
                        }

                        if (appr) {


                            // se chegarmos até aqui é aprovado
                            // vamos em frente para o registro
                            // Vamos calcular a taxa de inscrição.
                            // Mais pagam uma taxa menor.
                            if (candidate.getExp() <= 1) {
                                candidate.setRegistrationFee(500);
                            } else if (candidate.getExp() >= 2 && candidate.getExp() <= 3) {
                                candidate.setRegistrationFee(250);
                            } else if (candidate.getExp() >= 4 && candidate.getExp() <= 5) {
                                candidate.setRegistrationFee(100);
                            } else if (candidate.getExp() >= 6 && candidate.getExp() <= 9) {
                                candidate.setRegistrationFee(50);
                            } else {
                                candidate.setRegistrationFee(0);
                            }


                            // Agora salvar o objeto no banco
                            try {
                                candidateId = repository.save(candidate);
                            } catch (Exception e) {
                                //Caso ocorra algum erro
                                e.printStackTrace();
                            }
                        } else {
                            throw new NoSessionsApprovedException("No sessions approved.");
                        }
                    } else {
                        throw new CandidateDoesntMeetRequirementsException("Candidate doesn't meet our abitrary and capricious standards.");
                    }

                } else {
                    throw new IllegalArgumentException("Email is required.");
                }
            } else {
                throw new IllegalArgumentException("Last name is required.");
            }
        } else {
            throw new IllegalArgumentException("First Name is required");
        }

        //Se chegou até aqui ocorreu tudo certo e esta gravado no banco
        return candidateId;
    }

    public class CandidateDoesntMeetRequirementsException extends RuntimeException {
        public CandidateDoesntMeetRequirementsException(String message) {
            super(message);
        }

        public CandidateDoesntMeetRequirementsException(String format, Object[] args) {
            super(String.format(format, args));
        }

    }

    public class NoSessionsApprovedException extends RuntimeException {


        public NoSessionsApprovedException(String message) {
            super(message);
        }
    }
}
