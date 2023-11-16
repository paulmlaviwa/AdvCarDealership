package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String ContractFilePath = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ContractFilePath, true))) {
            if (contract instanceof SalesContract) {
                saveSalesContract((SalesContract) contract, writer);
            } else if (contract instanceof LeaseContract) {
                saveLeaseContract((LeaseContract) contract, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSalesContract(SalesContract salesContract, BufferedWriter writer) throws IOException {
        writer.write("SALES|");
        writeCommonContractData(salesContract, writer);
        writer.write(salesContract.getSalesTaxAmount() + "|");
        writer.write(salesContract.getRecordingFee() + "|");
        writer.write(salesContract.getProcessingFee() + "|");
        writer.write(salesContract.isFinanceOption() + "\n");
    }

    private void saveLeaseContract(LeaseContract leaseContract, BufferedWriter writer) throws IOException {
        writer.write("LEASE|");
        writeCommonContractData(leaseContract, writer);
        writer.write(leaseContract.getExpectedEndingValue() + "|");
        writer.write(leaseContract.getLeaseFee() + "\n");
    }

    private void writeCommonContractData(Contract contract, BufferedWriter writer) throws IOException {
        writer.write(contract.getDate() + "|");
        writer.write(contract.getCustomerName() + "|");
        writer.write(contract.getCustomerEmail() + "|");
        writer.write(contract.getVehicle().getVin() + "|");
        writer.write(contract.getTotalPrice() + "|");
        writer.write(contract.getMonthlyPayment() + "\n");
    }
}
