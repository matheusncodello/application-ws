package com.sptech.applicationws.infra.configurations.mapper;

import com.sptech.applicationws.domain.Campaign;
import com.sptech.applicationws.domain.Donation;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

@Component
public class CsvMapper {

    public void writeCampaignCsv(ListObj<Campaign> list, String nameArq) {
        FileWriter arq = null;
        Formatter output = null;
        nameArq += ".csv";
        boolean error = false;

        try {
            arq = new FileWriter(nameArq);
            output = new Formatter(arq);
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            output.format("CAMPAIGN_ID;NAME;DESCRIPTION;CREATED_CAMPAIGN;TYPE;ACTIVE%n%n");
            for (int i=0; i < list.getSize(); i++) {

                Campaign c = list.getElement(i);
                output.format("%d;%s;%s;%s;%s;%s%n",
                        c.getCampaignId(),
                        c.getCampaignName(),
                        c.getCampaignDescription(),
                        c.getCampaignTime(),
                        c.getCampaignType(),
                        c.getActive()
                        );
            }
        }
        catch (FormatterClosedException e) {
            System.out.println("Erro ao gravar o arquivo");
            error = true;
        }
        finally {
            output.close();
            try {
                arq.close();
            }
            catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                error = true;
            }
            if (error) {
                System.exit(1);
            }
        }
    }

    public void writeDonationCsv(ListObj<Donation> list, String nameArq) {
        FileWriter arq = null;
        Formatter output = null;
        nameArq += ".csv";
        boolean error = false;

        try {
            arq = new FileWriter(nameArq);
            output = new Formatter(arq);
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            output.format("DONATION_ID;NAME;DESCRIPTION;CREATED_DONATION;TYPE;ACTIVE%n%n");
            for (int i=0; i < list.getSize(); i++) {

                Donation d = list.getElement(i);

                output.format("%d;%s;%s;%s;%s;%s%n",
                        d.getDonationId(),
                        d.getDonationName(),
                        d.getDonationDescription(),
                        d.getDonationTime(),
                        d.getDonationType(),
                        d.getActive()
                );
            }
        }
        catch (FormatterClosedException e) {
            System.out.println("Erro ao gravar o arquivo");
            error = true;
        }
        finally {
            output.close();
            try {
                arq.close();
            }
            catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                error = true;
            }
            if (error) {
                System.exit(1);
            }
        }
    }
}
