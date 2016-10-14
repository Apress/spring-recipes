package com.apress.springrecipes.distributedspring.gridgain;

import org.gridgain.grid.GridException;
import org.gridgain.grid.GridJob;
import org.gridgain.grid.GridJobAdapter;
import org.gridgain.grid.GridJobResult;
import org.gridgain.grid.gridify.GridifyArgument;
import org.gridgain.grid.gridify.GridifyTaskSplitAdapter;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MultipleSalutationTask extends GridifyTaskSplitAdapter<String[]> {
    private static final long serialVersionUID = 1L;

    protected Collection<?extends GridJob> split(int i, final GridifyArgument gridifyArgument)
        throws GridException {
        Collection<GridJob> jobs = new ArrayList<GridJob>();
        Object[] params = gridifyArgument.getMethodParameters();
        String[] names = (String[]) params[0];

        for (final String n : names) {
            jobs.add(new GridJobAdapter<String>(n) {
                    private static final long serialVersionUID = 1L;

                    public Serializable execute() throws GridException {
                        SalutationService service = (SalutationService) gridifyArgument.getTarget();

                        return service.saluteSomeoneInForeignLanguage(n);
                    }
                });
        }

        return jobs;
    }

    public String[] reduce(List<GridJobResult> gridJobResults)
        throws GridException {
        Collection<String> res = new ArrayList<String>();

        for (GridJobResult result : gridJobResults) {
            String data = result.getData();
            res.add(data);
        }

        return res.toArray(new String[res.size()]);
    }
}
