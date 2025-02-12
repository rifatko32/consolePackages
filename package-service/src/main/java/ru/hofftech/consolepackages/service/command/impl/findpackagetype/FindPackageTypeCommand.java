package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.model.entity.PackageType;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import java.util.ArrayList;

/**
 * Command to find package type by name.
 */
@RequiredArgsConstructor
public class FindPackageTypeCommand implements Command {

    private final FindPackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;
    private final ReportWriterFactory reportWriterFactory;

    /**
     * Finds package type by id.
     */
    @Override
    public void execute() {
        var result = new ArrayList<PackageType>();

        if (context.getId() == null) {
            result.addAll(packageTypeRepository.findAll());
        } else {
            var packageType = packageTypeRepository.findById(context.getId()).orElse(null);

            if (packageType == null) {
                return;
            }

            result.add(packageType);
        }

        var report = new PlaneStringReport();
        for (PackageType packageType : result) {
            report.addReportString(packageType.toString());
        }

        var reportWriter = reportWriterFactory.createReportWriter(context.getReportOutputChannelType(), null);

        if (reportWriter != null) {
            reportWriter.writeReport(report);
        }

        context.setResult(result);
    }
}
