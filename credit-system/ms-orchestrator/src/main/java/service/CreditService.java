package service;




import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.*;

@ApplicationScoped
public class CreditService {

    @Inject
    @RestClient
    RiskClient riskClient;

    @Inject
    CreditEvaluationRepository repository;

    public CreditResponse evaluate(CreditRequest request) {

        validate(request);

        Map<String, Object> scoreResponse =
                (Map<String, Object>) riskClient.getScore(request.cedula);

        Map<String, Object> debtResponse =
                (Map<String, Object>) riskClient.getDebts(request.cedula);

        int score = (int) scoreResponse.get("score");

        List<Map<String, Object>> debts =
                (List<Map<String, Object>>) debtResponse.get("debts");

        double totalDebt = debts.stream()
                .mapToDouble(d -> (int) d.get("monthly"))
                .sum();

        CreditResponse response = new CreditResponse();
        response.score = score;

        if (score > 70 && (totalDebt + request.monto) < (request.salario * 0.40)) {
            response.estado = "APROBADO";
        } else {
            response.estado = "RECHAZADO";
        }

        // Guardar en BD
        CreditEvaluation entity = new CreditEvaluation();
        entity.cedula = request.cedula;
        entity.monto = request.monto;
        entity.estado = response.estado;
        entity.score = response.score;

        repository.persist(entity);

        return response;
    }

    public List<CreditEvaluation> getAll() {
        return repository.listAllEvaluations();
    }

    private void validate(CreditRequest request) {

        if (!CedulaValidator.isValid(request.cedula)) {
            throw new RuntimeException("Cédula inválida");
        }

        if (request.monto <= 0) {
            throw new RuntimeException("Monto inválido");
        }

        if (request.salario <= 0) {
            throw new RuntimeException("Salario inválido");
        }
    }
}