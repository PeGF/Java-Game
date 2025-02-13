import Nave.*;

import java.util.Iterator;

import java.awt.Color;
import java.util.ArrayList;

public class Main {

	/* Constantes relacionadas aos estados que os elementos   */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;


	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */

	public static void busyWait(long time){

		while(System.currentTimeMillis() < time) Thread.yield();
	}

	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */

	public static int findFreeIndex(int [] stateArray){

		int i;

		for(i = 0; i < stateArray.length; i++){

			if(stateArray[i] == INACTIVE) break;
		}

		return i;
	}

	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array, referentes a posições "inativas".               */

	public static int [] findFreeIndex(int [] stateArray, int amount){

		int i, k;
		int [] freeArray = { stateArray.length, stateArray.length, stateArray.length };

		for(i = 0, k = 0; i < stateArray.length && k < amount; i++){

			if(stateArray[i] == INACTIVE) {

				freeArray[k] = i;
				k++;
			}
		}

		return freeArray;
	}

	/* Método principal */

	public static void main(String [] args){

		/* Indica que o jogo está em execução */
		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */

		long delta;
		long currentTime = System.currentTimeMillis();

		/* variáveis do player */

		int player_max_bullets = 10;
		BulletsManager player_bullets = new BulletsManager(player_max_bullets);

		Player player = new Player(GameLib.WIDTH, GameLib.HEIGHT, player_bullets, currentTime);

		/*
		int player_state = ACTIVE;								// estado
		double player_X = GameLib.WIDTH / 2.0;					// coordenada x
		double player_Y = GameLib.HEIGHT * 0.90;				// coordenada y
		double player_VX = 0.25;								// velocidade no eixo x
		double player_VY = 0.25;								// velocidade no eixo y
		double player_radius = 12.0;							// raio (tamanho aproximado do player)
		double player_explosion_start = 0;						// instante do início da explosão
		double player_explosion_end = 0;						// instante do final da explosão
		long player_nextShot = currentTime;						// instante a partir do qual pode haver um próximo tiro

		 */

		/* variáveis dos projéteis disparados pelo player */

		/*
		int [] projectile_states = new int[10];					// estados
		double [] projectile_X = new double[10];				// coordenadas x
		double [] projectile_Y = new double[10];				// coordenadas y
		double [] projectile_VX = new double[10];				// velocidades no eixo x
		double [] projectile_VY = new double[10];				// velocidades no eixo y

		 */

		/* variáveis dos inimigos tipo 1 */

		/*
		int [] enemy1_states = new int[10];						// estados
		double [] enemy1_X = new double[10];					// coordenadas x
		double [] enemy1_Y = new double[10];					// coordenadas y
		double [] enemy1_V = new double[10];					// velocidades
		double [] enemy1_angle = new double[10];				// ângulos (indicam direção do movimento)
		double [] enemy1_RV = new double[10];					// velocidades de rotação
		double [] enemy1_explosion_start = new double[10];		// instantes dos inícios das explosões
		double [] enemy1_explosion_end = new double[10];		// instantes dos finais da explosões
		long [] enemy1_nextShoot = new long[10];				// instantes do próximo tiro
		double enemy1_radius = 9.0;								// raio (tamanho do inimigo 1)
		long nextEnemy1 = currentTime + 2000;					// instante em que um novo inimigo 1 deve aparecer
		*/

		ArrayList<Enemy1> enemies1 = new ArrayList<>();

		/* variáveis dos inimigos tipo 2 */

		/*
		int [] enemy2_states = new int[10];						// estados
		double [] enemy2_X = new double[10];					// coordenadas x
		double [] enemy2_Y = new double[10];					// coordenadas y
		double [] enemy2_V = new double[10];					// velocidades
		double [] enemy2_angle = new double[10];				// ângulos (indicam direção do movimento)
		double [] enemy2_RV = new double[10];					// velocidades de rotação
		double [] enemy2_explosion_start = new double[10];		// instantes dos inícios das explosões
		double [] enemy2_explosion_end = new double[10];		// instantes dos finais das explosões
		double enemy2_spawnX = GameLib.WIDTH * 0.20;			// coordenada x do próximo inimigo tipo 2 a aparecer
		int enemy2_count = 0;									// contagem de inimigos tipo 2 (usada na "formação de voo")
		double enemy2_radius = 12.0;							// raio (tamanho aproximado do inimigo 2)
		long nextEnemy2 = currentTime + 7000;					// instante em que um novo inimigo 2 deve aparecer
		*/

	    ArrayList<Enemy2> enemies2 = new ArrayList<>();

		/* variáveis dos projéteis lançados pelos inimigos (tanto tipo 1, quanto tipo 2) */

		/*
		int [] e_projectile_states = new int[200];				// estados
		double [] e_projectile_X = new double[200];				// coordenadas x
		double [] e_projectile_Y = new double[200];				// coordenadas y
		double [] e_projectile_VX = new double[200];			// velocidade no eixo x
		double [] e_projectile_VY = new double[200];			// velocidade no eixo y
		double e_projectile_radius = 2.0;						// raio (tamanho dos projéteis inimigos)

		 */

		int enemy_max_bullets = 200;
		BulletsManager enemy_bullets = new BulletsManager(enemy_max_bullets);

		/* estrelas que formam o fundo de primeiro plano */

		Background plano1 = new Background(20, 0.070);

		/* estrelas que formam o fundo de segundo plano */

		Background plano2 = new Background(50, 0.045);

		/* inicializações */

		for(int i = 0; i < plano1.getX().length; i++){

			plano1.setX(i, GameLib.WIDTH);
			plano1.setY(i, GameLib.HEIGHT);
		}

		for(int i = 0; i < plano2.getX().length; i++){

			plano2.setX(i, GameLib.WIDTH);
			plano2.setY(i, GameLib.HEIGHT);
		}

		/* iniciado interface gráfica */

		GameLib.initGraphics();

		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/*                                                                                               */
		/* O main loop do jogo possui executa as seguintes operações:                                    */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
		/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/

		int enemyQuantity = 10;
		long nextEnemy1 = System.currentTimeMillis() + 2000;
		long nextEnemy2 = System.currentTimeMillis() + 7000;

		while(running){

			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */

			delta = System.currentTimeMillis() - currentTime;

			/* Já a variável "currentTime" nos dá o timestamp atual.  */

			currentTime = System.currentTimeMillis();

			/***************************/
			/* Verificação de colisões */
			/***************************/

			if(player.getState() == ACTIVE){

				/* colisões player - projeteis (inimigo) */

				/*
				for(int i = 0; i < e_projectile_states.length; i++){

					double dx = e_projectile_X[i] - player_X;
					double dy = e_projectile_Y[i] - player_Y;
					double dist = Math.sqrt(dx * dx + dy * dy);

					if(dist < (player_radius + e_projectile_radius) * 0.8){

						player_state = EXPLODING;
						player_explosion_start = currentTime;
						player_explosion_end = currentTime + 2000;
					}
				}

				 */

				/* colisões player - inimigos */


//				for(int i = 0; i < enemy1_states.length; i++){
//
//					double dx = enemy1_X[i] - player_X;
//					double dy = enemy1_Y[i] - player_Y;
//					double dist = Math.sqrt(dx * dx + dy * dy);
//
//					if(dist < (player_radius + enemy1_radius) * 0.8){
//
//						player_state = EXPLODING;
//						player_explosion_start = currentTime;
//						player_explosion_end = currentTime + 2000;
//					}
//				}

//				for(int i = 0; i < enemy2_states.length; i++){
//
//					double dx = enemy2_X[i] - player_X;
//					double dy = enemy2_Y[i] - player_Y;
//					double dist = Math.sqrt(dx * dx + dy * dy);
//
//					if(dist < (player_radius + enemy2_radius) * 0.8){
//
//						player_state = EXPLODING;
//						player_explosion_start = currentTime;
//						player_explosion_end = currentTime + 2000;
//					}
//				}
			}

			/* colisões projeteis (player) - inimigos */

//			for(int k = 0; k < projectile_states.length; k++){
//
//				for(int i = 0; i < enemy1_states.length; i++){
//
//					if(enemy1_states[i] == ACTIVE){
//
//						double dx = enemy1_X[i] - projectile_X[k];
//						double dy = enemy1_Y[i] - projectile_Y[k];
//						double dist = Math.sqrt(dx * dx + dy * dy);
//
//						if(dist < enemy1_radius){
//
//							enemy1_states[i] = EXPLODING;
//							enemy1_explosion_start[i] = currentTime;
//							enemy1_explosion_end[i] = currentTime + 500;
//						}
//					}
//				}



//				for(int i = 0; i < enemy2_states.length; i++){
//
//					if(enemy2_states[i] == ACTIVE){
//
//						double dx = enemy2_X[i] - projectile_X[k];
//						double dy = enemy2_Y[i] - projectile_Y[k];
//						double dist = Math.sqrt(dx * dx + dy * dy);
//
//						if(dist < enemy2_radius){
//
//							enemy2_states[i] = EXPLODING;
//							enemy2_explosion_start[i] = currentTime;
//							enemy2_explosion_end[i] = currentTime + 500;
//						}
//					}
//				}
//			}

			/***************************/
			/* Atualizações de estados */
			/***************************/

			/* projeteis (player) */

			for(Bullet player_bullet : player_bullets.getBullets()){

				if(player_bullet.getState() == ACTIVE){

					/* verificando se projétil saiu da tela */
					if(player_bullet.getY() < 0) {

						player_bullet.setState(INACTIVE);
					}
					else {

						player_bullet.updatePosition(delta);
					}
				}
			}

			/* projeteis (inimigos) */

			for(Bullet bullet : enemy_bullets.getBullets()){

				bullet.updatePosition(delta);
			}

			/* inimigos tipo 1 */

			for(Iterator<Enemy1> iterator = enemies1.iterator(); iterator.hasNext();){
				Enemy1 enemy = iterator.next();
				if(enemy.getState() == EXPLODING){

					if(currentTime > enemy.getExplosion_end()){

						enemy.setState(INACTIVE);
						iterator.remove();
					}
				}

				if(enemy.getState() == ACTIVE){

					/* verificando se inimigo saiu da tela */
					if(enemy.getY() > GameLib.HEIGHT + 10) {

						enemy.setState(INACTIVE);
						iterator.remove();
					}
					else {

						enemy.updatePosition(delta);

						if (currentTime > enemy.getNext_shoot() && enemy.getY() < player.getY()) {
							if (enemy_bullets.canShoot()) {
								enemy.shoot(enemy.getAngle()); // Angulo de disparo, pode ser ajustado conforme necessário
								enemy.setNext_shoot((long) (currentTime + 200 + Math.random() * 500));
							}
						}
					}
				}
			}
			enemy_bullets.updateBullets(delta, GameLib.WIDTH, GameLib.HEIGHT);

			/* inimigos tipo 2 */

			for(Iterator<Enemy2> iterator = enemies2.iterator(); iterator.hasNext();){
				Enemy2 enemy = iterator.next();
				if(enemy.getState() == EXPLODING){

					if(currentTime > enemy.getExplosion_end()){

						enemy.setState(INACTIVE);
						iterator.remove();
					}
				}

				if(enemy.getState() == ACTIVE){

					/* verificando se inimigo saiu da tela */
					if(enemy.getX() < -10 || enemy.getX() > GameLib.WIDTH + 10 ) {

						enemy.setState(INACTIVE);
						iterator.remove();
					}
					else {

						// boolean shootNow = false;
						double previousY = enemy.getY();

						enemy.updatePosition(delta);

						double threshold = GameLib.HEIGHT * 0.30;

						if(previousY < threshold && enemy.getY() >= threshold) {

							if(enemy.getX() < GameLib.WIDTH / 2.0) enemy.setRV(0.003);
							else enemy.setRV(-0.003);
						}

						if(enemy.getRV() > 0 && Math.abs(enemy.getAngle() - 3 * Math.PI) < 0.05){

							enemy.setRV(0.0);
							enemy.setAngle(3 * Math.PI);
							enemy.setShootNow(true);
						}

						if(enemy.getRV() < 0 && Math.abs(enemy.getAngle()) < 0.05){

							enemy.setRV(0.0);
							enemy.setAngle(0.0);
							enemy.setShootNow(true);
						}

						if(enemy.isShootNow()){

							double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };

							for(double angle : angles){

								if (enemy.getBulletsManager().canShoot()) {
									enemy.shoot(angle);
								}
							}
							enemy.setShootNow(false);

						}
					}
				}
			}

			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */
			if(currentTime > nextEnemy1){

				if(enemies1.size() < enemyQuantity){

					enemies1.add(new Enemy1(ACTIVE, (Math.random() * (GameLib.WIDTH - 20.0) + 10.0), -10.0,
							0.20 + Math.random() * 0.15, currentTime, enemy_bullets));
					Enemy1 lastAddedEnemy = enemies1.get(enemies1.size() - 1);
					lastAddedEnemy.setNext_shoot(currentTime + 500);
					nextEnemy1 = currentTime + 500;
				}
			}

			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */

			if(currentTime > nextEnemy2){


				if(enemies2.size() < enemyQuantity){

					enemies2.add(new Enemy2(ACTIVE, GameLib.WIDTH * 0.20, -10.0, 0.42, 0.0, currentTime, enemy_bullets));
					Enemy2 lastAddedEnemy = enemies2.get(enemies2.size() - 1);

					if(Enemy2.getCount() < 10){

						nextEnemy2 = currentTime + 120;
					}
					else {

						Enemy2.setCount(0);
						lastAddedEnemy.setSpawnX(Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8);
						nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
					}
				}
			}

			/* Verificando se a explosão do player já acabou.         */
			/* Ao final da explosão, o player volta a ser controlável */
			if(player.getState() == EXPLODING){

				if(currentTime > player.getExplosionEnd()){

					player.setState(ACTIVE);
				}
			}

			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/

			if(player.getState() == ACTIVE){

				if(GameLib.iskeyPressed(GameLib.KEY_UP)) player.setY(player.getY() - delta * player.getVy());
				if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) player.setY(player.getY() + delta * player.getVy());
				if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) player.setX(player.getX() - delta * player.getVx());
				if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) player.setX(player.getX() + delta * player.getVx());
				if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

					if(currentTime > player.getNextShot()){


						if(player_bullets.getBullets().size() < player_max_bullets){
							player.shoot();
						}
					}
				}
			}

			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;

			/* Verificando se coordenadas do player ainda estão dentro	*/
			/* da tela de jogo após processar entrada do usuário.       */

			if(player.getX() < 0.0) player.setX(0.0);
			if(player.getX() >= GameLib.WIDTH) player.setX(GameLib.WIDTH - 1);
			if(player.getY() < 25.0) player.setY(25.0);
			if(player.getY() >= GameLib.HEIGHT) player.setY(GameLib.HEIGHT - 1);

			/*******************/
			/* Desenho da cena */
			/*******************/

			/* desenhando plano fundo distante */

			GameLib.setColor(Color.DARK_GRAY);
			plano2.setCount(delta);

			for(int i = 0; i < plano2.getX().length; i++){

				GameLib.fillRect(plano2.getX()[i], (plano2.getY()[i] + plano2.getCount()) % GameLib.HEIGHT, 2, 2);
			}

			/* desenhando plano de fundo próximo */

			GameLib.setColor(Color.GRAY);
			plano1.setCount(delta);

			for(int i = 0; i < plano1.getX().length; i++){

				GameLib.fillRect(plano1.getX()[i], (plano1.getY()[i] + plano1.getCount()) % GameLib.HEIGHT, 3, 3);
			}

			/* desenhando player */

			if(player.getState() == EXPLODING){

				double alpha = (currentTime - player.getExplosionStart()) / (player.getExplosionEnd() - player.getExplosionStart());
				GameLib.drawExplosion(player.getX(), player.getY(), alpha);
			}
			else{

				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(player.getX(), player.getY(), player.getRadius());
			}


			/* deenhando projeteis (player) */

			for(Bullet bullets : player_bullets.getBullets()){

				if(bullets.getState() == ACTIVE){

					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(bullets.getX(), bullets.getY() - 5, bullets.getX(), bullets.getY() + 5);
					GameLib.drawLine(bullets.getX() - 1, bullets.getY() - 3, bullets.getX() - 1, bullets.getY() + 3);
					GameLib.drawLine(bullets.getX() + 1, bullets.getY() - 3, bullets.getX() + 1, bullets.getY() + 3);
				}
			}

			/* desenhando projeteis (inimigos) */

			for(Bullet bullet : enemy_bullets.getBullets()){

				if(bullet.getState() == 1){

					GameLib.setColor(Color.RED);
					GameLib.drawCircle(bullet.getX(), bullet.getY(), bullet.getRadius());
				}
			}

			/* desenhando inimigos (tipo 1) */

			for(Enemy1 enemy : enemies1){

				if(enemy.getState() == EXPLODING){

					double alpha = (currentTime - enemy.getExplosion_start()) / (enemy.getExplosion_end() - enemy.getExplosion_start());
					GameLib.drawExplosion(enemy.getX(), enemy.getY(), alpha);
				}

				if(enemy.getState() == ACTIVE){

					GameLib.setColor(Color.CYAN);
					GameLib.drawCircle(enemy.getX(), enemy.getY(), enemy.getRadius());
				}
			}

			/* desenhando inimigos (tipo 2) */

			for(Enemy2 enemy : enemies2){

				if(enemy.getState() == EXPLODING){

					double alpha = (currentTime - enemy.getExplosion_start()) / (enemy.getExplosion_end() - enemy.getExplosion_start());
					GameLib.drawExplosion(enemy.getX(), enemy.getY(), alpha);
				}

				if(enemy.getState() == ACTIVE){

					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(enemy.getX(), enemy.getY(), enemy.getRadius());
				}
			}

			/* chamama a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */

			GameLib.display();

			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 5 ms. */

			busyWait(currentTime + 5);
		}

		System.exit(0);
	}
}