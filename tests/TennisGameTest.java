import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertNotNull("Score should not be null", score);
	    assertFalse("Score should not be empty", score.isEmpty());
	    assertEquals("Initial score incorrect", "love - love", score);
	}
	
	
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		assertThrows(TennisGameException.class, () -> game.player1Scored());		
	}
	
	@Test
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Assert
		assertThrows(TennisGameException.class, () -> game.player2Scored());		
	}
	
	@Test
	public void testTEnnisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Assert
		assertEquals("player1 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Assert
		assertEquals("player2 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player1HasAdvantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		//Assert
		assertEquals("player1 has advantage", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player2HasAdvantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		//Assert
		assertEquals("player2 has advantage", game.getScore());
	}
	
	@Test
	public void testTennisGame_getScore_15() throws TennisGameException {
//		Arrange
		TennisGame game = new TennisGame();
//		Act
		game.player1Scored();
//		Assert
		assertEquals("15 - love", game.getScore());
	}
	
	@Test
	public void testTennisGame_getScore_30() throws TennisGameException {
//		Arrange
		TennisGame game = new TennisGame();
//		Act
		game.player1Scored();
		game.player1Scored();
//		Assert
		assertEquals("30 - love", game.getScore());
	}
	
	@Test
	public void testTennisGame_getScore_40() throws TennisGameException {
//		Arrange
		TennisGame game = new TennisGame();
//		Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
//		Assert
		assertEquals("40 - love", game.getScore());
	}
	
	@Test
	public void testTeenisGame_CheckGameEnded_Player1WinsWithExactScoreDifference() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3
	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player1Scored(); // 4
	    
	    // Act & Assert
	    assertEquals("player1 wins", game.getScore());
	}

	@Test
	public void testCheckGameEnded_Player1DoesNotWinWithInsufficientDifference() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    
	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3
	    
	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player2Scored(); // 3
	    
	    game.player1Scored(); // 4
	    game.player2Scored(); // 4
	    game.player1Scored(); // 5
	    

	    // Act & Assert
	    assertNotEquals("player1 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_CheckGameEnded_Player2WinsWithExactScoreDifference() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player2Scored(); // 3
	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player2Scored(); // 4

	    // Act & Assert
	    assertEquals("player2 wins", game.getScore());
	}

	@Test
	public void testTennisGame_CheckGameEnded_Player2DoesNotWinWithInsufficientDifference() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();

	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player2Scored(); // 3

	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3

	    game.player2Scored(); // 4
	    game.player1Scored(); // 4
	    game.player2Scored(); // 5

	    // Act & Assert
	    assertNotEquals("player2 wins", game.getScore());
	}
	
	@Test
	public void testTennisGame_Player1WinsWithMinimumPoints() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    
	    // Act
	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3
	    game.player1Scored(); // 4
	    
	    assertEquals("player1 wins", game.getScore());
	}


	@Test
	public void testTennisGame_Player1AdvantageExactlyOnePoint() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    
	    // Act
	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3
	    
	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player2Scored(); // 3
	    game.player1Scored(); // 4

	    assertEquals("player1 has advantage", game.getScore());
	}

	@Test
	public void testTennisGame_Player2AdvantageExactlyOnePoint() throws TennisGameException {
	    // Arrange
	    TennisGame game = new TennisGame();
	    
	    // Act
	    game.player2Scored(); // 1
	    game.player2Scored(); // 2
	    game.player2Scored(); // 3
	    
	    game.player1Scored(); // 1
	    game.player1Scored(); // 2
	    game.player1Scored(); // 3
	    game.player2Scored(); // 4

	    assertEquals("player2 has advantage", game.getScore());
	}
}

