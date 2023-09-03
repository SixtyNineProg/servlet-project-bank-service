package by.clevertec.klimov.cleverbank.app.processor;

import by.clevertec.klimov.cleverbank.entity.User;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class InterestCalculator {
  private static final int PERIOD = 30;

  private int interestRate = 1;
  
  private List<User> userList;
  private ScheduledExecutorService executorService;

  public InterestCalculator(int interestRate, List<User> userList) {
    this.interestRate = interestRate;
    this.userList = userList;
  }

  public void startInterestCalculation() {
    executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(this::checkAndCalculateInterest, 0, PERIOD, TimeUnit.SECONDS);
  }

  public void stopInterestCalculation() {
    executorService.shutdown();
  }

  private void checkAndCalculateInterest() {
    LocalDateTime now = LocalDateTime.now();
    if (now.equals(now.with(TemporalAdjusters.lastDayOfMonth()))) {
      CompletableFuture.runAsync(this::calculateInterestAsync);
    }
  }

  private void calculateInterestAsync() {
    userList.forEach(user -> user.calculateInterest(interestRate));
  }
}
