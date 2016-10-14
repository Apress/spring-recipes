package com.apress.springrecipes.sequence;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

public class SequenceGenerator {

    private List<Integer> suffixes;
    private int initial;
    private int counter;

    @Resource(name = "datePrefixGenerator")
    private PrefixGenerator prefixGenerator;

    public void setSuffixes(List<Integer> suffixes) {
        this.suffixes = suffixes;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public synchronized String getSequence() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefixGenerator.getPrefix());
        buffer.append(initial + counter++);
        DecimalFormat formatter = new DecimalFormat("0000");
        for (int suffix : suffixes) {
            buffer.append("-");
            buffer.append(formatter.format(suffix));
        }
        return buffer.toString();
    }
}
