/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.model.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.jtalks.jcommune.model.entity.Poll;
import org.jtalks.jcommune.model.entity.PollItem;
import org.jtalks.jcommune.model.validation.validators.PollFilledCompletelyValidator;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Anuar_Nurmakanov
 *
 */
public class PollFilledCompletelyValidatorTest {
    @Mock
    private ConstraintValidatorContext validatorContext;
    private PollFilledCompletelyValidator validator;
    
    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.validator = new PollFilledCompletelyValidator();
    }
    
    @Test
    public void testPollWithEmptyTitleAndEmptyItemsIsValid() {
        String title = StringUtils.EMPTY;
        List<PollItem> items = Collections.emptyList();
        Poll poll = new Poll(title);
        poll.setPollItems(items);
        
        boolean isValid = validator.isValid(poll, validatorContext);
        
        Assert.assertTrue(isValid, "Poll with empty title and empty items must be valid");
    }
    
    @Test
    public void testPollWithFilledTitleAndFilledItemsIsValid() {
        String title = "larks! Stands and deliver.";
        List<PollItem> items = Arrays.asList(new PollItem("larks"));
        Poll poll = new Poll(title);
        poll.setPollItems(items);
        
        boolean isValid = validator.isValid(poll, validatorContext);
        
        Assert.assertTrue(isValid, "Poll with filled title and filled items must be valid");
    }
    
    @Test
    public void testPollWithFilledTitleAndEmptyItemsIsInvalid() {
        String title = "larks! Stands and deliver.";
        List<PollItem> items = Collections.emptyList();
        Poll poll = new Poll(title);
        poll.setPollItems(items);
        
        boolean isValid = validator.isValid(poll, validatorContext);
        
        Assert.assertFalse(isValid, 
                "Poll has filled title, but the list of items is empty, so it must be invalid");
    }
    
    @Test
    public void testPollWithEmptyTitleAndFilledItemsIsInvalid() {
        String title = StringUtils.EMPTY;
        List<PollItem> items = Arrays.asList(new PollItem("larks"));
        Poll poll = new Poll(title);
        poll.setPollItems(items);
        
        boolean isValid = validator.isValid(poll, validatorContext);
        
        Assert.assertFalse(isValid, 
                "Poll has the filled list of items, but title is empty, so it must be invalid");
    }
}
