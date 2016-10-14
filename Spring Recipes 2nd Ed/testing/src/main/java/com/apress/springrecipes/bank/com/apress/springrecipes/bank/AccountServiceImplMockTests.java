package com.apress.springrecipes.bank;

import org.easymock.EasyMock; //import org.easymock.MockControl;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceImplMockTests {

    private static final String TEST_ACCOUNT_NO = "1234";
    private EasyMock easyMock; //private MockControl mockControl;
    private AccountDao accountDao;
    private AccountService accountService;

    @Before
    public void init() {
        accountDao = easyMock.createMock(AccountDao.class);
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    public void deposit() {
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        accountDao.findAccount(TEST_ACCOUNT_NO);
        easyMock.expectLastCall().andReturn(account);//mockControl.setReturnValue(account);
        account.setBalance(150);
        accountDao.updateAccount(account);
        easyMock.replay();

        accountService.deposit(TEST_ACCOUNT_NO, 50);
        easyMock.verify();
    }

    @Test
    public void withdrawWithSufficientBalance() {
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        accountDao.findAccount(TEST_ACCOUNT_NO);
        easyMock.expectLastCall().andReturn(account);//mockControl.setReturnValue(account);
        account.setBalance(50);
        accountDao.updateAccount(account);
        easyMock.replay();

        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        easyMock.verify();
    }

    @Test(expected = InsufficientBalanceException.class)
    public void testWithdrawWithInsufficientBalance() {
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        accountDao.findAccount(TEST_ACCOUNT_NO);
        easyMock.expectLastCall().andReturn(account);//mockControl.setReturnValue(account);
        easyMock.replay();

        accountService.withdraw(TEST_ACCOUNT_NO, 150);
        easyMock.verify();
    }
}
